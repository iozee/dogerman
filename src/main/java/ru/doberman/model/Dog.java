package ru.doberman.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author izerenev
 *         Date: 29.01.14
 */
@Entity
@Table(name = "dog")
@Access(AccessType.FIELD)
public class Dog extends NamedCommonEntity implements Serializable {
  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  @ManyToOne
  @JoinColumn(name = "breedId")
  private Breed breed;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "dog")
  private Set<DogImage> images;

  @Transient
  private DogImage avatar;

  @Column(name = "created")
  @Temporal(TemporalType.DATE)
  private Date created;

  @Column(name = "modified")
  @Temporal(TemporalType.DATE)
  private Date modified;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Breed getBreed() {
    return breed;
  }

  public void setBreed(Breed breed) {
    this.breed = breed;
  }

  public Set<DogImage> getImages() {
    return images;
  }

  public void setImages(Set<DogImage> images) {
    this.images = images;
  }

  public DogImage getAvatar() {
    if (avatar != null) {
      return avatar;
    }
    if (getImages() != null) {
      for (DogImage dogImage : getImages()) {
        if (dogImage.isAvatar()) {
          avatar = dogImage;
          return avatar;
        }
      }
    }
    return null;
  }

  public void setAvatar(DogImage avatar) {
    this.avatar = avatar;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getModified() {
    return modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }
}
