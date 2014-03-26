package ru.doberman.model;

import javax.persistence.*;

/**
 * @author izerenev
 *         Date: 10.03.14
 */
@Entity
@Table(name = "user_image")
@Access(AccessType.FIELD)
@PrimaryKeyJoinColumn(name = "id")
public class UserImage extends Image {
  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  @Column(name = "isAvatar")
  private boolean isAvatar;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public boolean isAvatar() {
    return isAvatar;
  }

  public void setAvatar(boolean isAvatar) {
    this.isAvatar = isAvatar;
  }
}
