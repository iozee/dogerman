package ru.doberman.model;

import javax.persistence.*;

/**
 * @author izerenev
 *         Date: 10.03.14
 */
@Entity
@Table(name = "dog_image")
@Access(AccessType.FIELD)
@PrimaryKeyJoinColumn(name = "id")
public class DogImage extends Image implements Cloneable {
  @ManyToOne
  @JoinColumn(name = "dogId")
  private Dog dog;

  @Column(name = "isAvatar")
  private boolean isAvatar;

  public Dog getDog() {
    return dog;
  }

  public void setDog(Dog dog) {
    this.dog = dog;
  }

  public boolean isAvatar() {
    return isAvatar;
  }

  public void setAvatar(boolean isAvatar) {
    this.isAvatar = isAvatar;
  }

  @Override
  public DogImage clone() {
    DogImage clone = new DogImage();
    clone.setDog(getDog());
    clone.setAvatar(isAvatar());
    return clone;
  }
}
