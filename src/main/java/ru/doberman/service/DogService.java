package ru.doberman.service;

import org.primefaces.model.UploadedFile;
import ru.doberman.dao.DogDao;
import ru.doberman.model.Breed;
import ru.doberman.model.Dog;

import java.io.IOException;
import java.util.List;

/**
 * @author izerenev
 *         Date: 12.03.14
 */
public interface DogService extends CommonService<Dog, DogDao> {
  Dog getRandomDog();

  int getUpVotes(Dog dog);

  int getDownVotes(Dog dog);

  void like(Dog dog);

  void dislike(Dog dog);

  void addDog(Dog dog, List<UploadedFile> uploadedImages) throws IOException;

  List<Breed> getAllBreeds();

  Breed getBreedByName(String name);

  List<Breed> getBreedsByName(String name);

  List<Dog> getMyDogs();

  void addImages(List<UploadedFile> uploadedImages, Dog dog) throws IOException;
}
