package ru.doberman.service;

import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.doberman.core.FileService;
import ru.doberman.core.ImageResizer;
import ru.doberman.core.SystemUtils;
import ru.doberman.dao.*;
import ru.doberman.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author izerenev
 *         Date: 30.01.14
 */
@Service
@Transactional
public class DogServiceImpl implements DogService {
  @Autowired
  protected DogDao dao;

  @Autowired
  private VoteDao voteDao;

  @Autowired
  private BreedDao breedDao;

  @Autowired
  private DogImageDao dogImageDao;

  @Autowired
  private ImageDao imageDao;

  @Qualifier("fileServiceFSImpl")
  @Autowired
  private FileService fileService;

  /**
   * Saves big image and for a dog
   *
   * @param image original image
   * @return file id
   * @throws java.io.IOException
   */
  private String saveBigDogImage(UploadedFile image) throws IOException {
    return saveImage(image,
        Integer.valueOf(SystemUtils.getProperty("image.big.width")),
        Integer.valueOf(SystemUtils.getProperty("image.big.height")),
        SystemUtils.getProperty("image.dog.large.path"));
  }

  /**
   * Saves thumb image for a dog
   *
   * @param image original image
   * @return file id
   * @throws IOException
   */
  private String saveThumbDogImage(UploadedFile image) throws IOException {
    return saveImage(image,
        Integer.valueOf(SystemUtils.getProperty("image.thumb.width")),
        Integer.valueOf(SystemUtils.getProperty("image.thumb.height")),
        SystemUtils.getProperty("image.dog.small.path"));
  }

  private String saveImage(UploadedFile image, int width, int height, String relativePath) throws IOException {
    relativePath = relativePath + image.getFileName();
    InputStream resizedImage = new ImageResizer().resize(image.getInputstream(), relativePath, width, height);
    return fileService.uploadFile(resizedImage, relativePath);
  }

  @Override
  public DogDao getDao() {
    return dao;
  }

  public void setDao(DogDao dao) {
    this.dao = dao;
  }

  @Override
  public Dog getById(int id) {
    return dao.getById(id);
  }

  @Override
  public List<Dog> getAll() {
    return dao.getAll();
  }

  public VoteDao getVoteDao() {
    return voteDao;
  }

  public void setVoteDao(VoteDao voteDao) {
    this.voteDao = voteDao;
  }

  public BreedDao getBreedDao() {
    return breedDao;
  }

  public void setBreedDao(BreedDao breedDao) {
    this.breedDao = breedDao;
  }

  public ImageDao getImageDao() {
    return imageDao;
  }

  public void setImageDao(ImageDao imageDao) {
    this.imageDao = imageDao;
  }

  public DogImageDao getDogImageDao() {
    return dogImageDao;
  }

  public void setDogImageDao(DogImageDao dogImageDao) {
    this.dogImageDao = dogImageDao;
  }

  public void like(Dog dog) {
    Vote vote = new Vote(null, dog, true);
    voteDao.create(vote);
  }

  public int getUpVotes(Dog dog) {
    return dao.getVotes(dog, true);
  }

  public int getDownVotes(Dog dog) {
    return dao.getVotes(dog, false);
  }

  public Dog getRandomDog() {
    return dao.getRandomDog();
  }

  public void dislike(Dog dog) {
    Vote downVote = new Vote(null, dog, false);
    voteDao.create(downVote);
  }

  @Override
  public void addDog(Dog dog, List<UploadedFile> uploadedImages) throws IOException {
    User user = SystemUtils.getCurrentUser();
    dog.setUser(user);
    dao.create(dog);
    addImages(uploadedImages, dog);
  }

  public List<Breed> getAllBreeds() {
    return breedDao.getAll();
  }

  public Breed getBreedByName(String name) {
    return breedDao.getBreedByName(name);
  }

  @Override
  public List<Breed> getBreedsByName(String name) {
    return breedDao.getBreedsByName(name);
  }

  @Override
  public List<Dog> getMyDogs() {
    User user = ((UserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    return dao.getMyDogs(user);
  }

  @Override
  public void addImages(List<UploadedFile> uploadedImages, Dog dog) throws IOException {
    // save attached images
    if (uploadedImages != null) {
      boolean isFirstImage = true;
      for (UploadedFile image : uploadedImages) {
        DogImage dogImage = new DogImage();
        dogImage.setPath(saveBigDogImage(image));
        dogImage.setThumb(saveThumbDogImage(image));
        dogImage.setDog(dog);
        // set first thumb as avatar
        dogImage.setAvatar(isFirstImage);
        if (isFirstImage) {
          isFirstImage = false;
        }
        dogImageDao.create(dogImage);
      }
    }
  }
}
