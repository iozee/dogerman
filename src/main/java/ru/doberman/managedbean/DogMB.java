package ru.doberman.managedbean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.doberman.core.SystemUtils;
import ru.doberman.core.fileupload.ImageUploadMultipleMB;
import ru.doberman.model.Breed;
import ru.doberman.model.Dog;
import ru.doberman.service.DogService;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * @author izerenev
 *         Date: 30.01.14
 */
@ManagedBean
@ViewScoped
@Component
public class DogMB extends CommonMB<Dog, DogService> {
  private static final Logger LOG = LoggerFactory.getLogger(DogMB.class);

  @Autowired
  private ImageUploadMultipleMB imageUploadMutiple;

  @Resource
  public void setService(DogService service) {
    this.service = service;
  }

  @Override
  public Class<Dog> getClazz() {
    return Dog.class;
  }

  public Dog getRandomDog() {
    return service.getRandomDog();
  }

  public int getUpVotes(Dog dog) {
    return service.getUpVotes(dog);
  }

  public int getDownVotes(Dog dog) {
    return service.getDownVotes(dog);
  }

  public void like() {
    getService().like(getEntity());
  }

  public void dislike() {
    getService().dislike(getEntity());
  }

  public void initRandomDog() {
    setEntity(getService().getRandomDog());
  }

  public String addDog() {
    try {
      service.addDog(getEntity(), imageUploadMutiple.getUploadedImages());
      resetEntity();
      SystemUtils.showMessage("Ваша собака успешно добавлена");
    } catch (Throwable e) {
      LOG.error(e.getMessage(), e);
      SystemUtils.showMessage("Произошла ошибка");
      return null;
    }
    return "myDogs?faces-redirect=true";
  }

  @Override
  public void resetEntity() {
    super.resetEntity();
    imageUploadMutiple.resetUploadedFiles();
  }

  public List<Breed> getBreeds() {
    return service.getAllBreeds();
  }

  public Breed getBreed() {
    return getEntity().getBreed();
  }

  public void setBreed(Breed breed) {
    getEntity().setBreed(breed);
  }

  public Breed getBreedByName(String name) {
    return service.getBreedByName(name);
  }

  public List<Breed> completeBreed(String query) {
    return service.getBreedsByName(query);
  }

  public ImageUploadMultipleMB getImageUploadMutiple() {
    return imageUploadMutiple;
  }

  public void setImageUploadMutiple(ImageUploadMultipleMB imageUploadMutiple) {
    this.imageUploadMutiple = imageUploadMutiple;
  }

  public List<Dog> getMyDogs() {
    return service.getMyDogs();
  }
}
