package ru.doberman.model;

import javax.persistence.*;

/**
 * @author izerenev
 *         Date: 17.02.14
 */
@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "dogId", "up"})})
public class Vote extends CommonEntity {
  private User userId;
  private Dog dogId;
  private boolean up;

  public Vote() {
  }

  public Vote(User userId, Dog dogId, boolean up) {
    this.userId = userId;
    this.dogId = dogId;
    this.up = up;
  }

  @ManyToOne
  @JoinColumn(name = "userId")
  public User getUserId() {
    return userId;
  }

  public void setUserId(User userId) {
    this.userId = userId;
  }

  @ManyToOne
  @JoinColumn(name = "dogId")
  public Dog getDogId() {
    return dogId;
  }

  public void setDogId(Dog dogId) {
    this.dogId = dogId;
  }

  public boolean isUp() {
    return up;
  }

  public void setUp(boolean up) {
    this.up = up;
  }
}
