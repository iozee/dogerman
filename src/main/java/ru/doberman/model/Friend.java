package ru.doberman.model;

import javax.persistence.*;

/**
 * @author izerenev
 *         Date: 25.03.2014
 */
@Entity
@Table(name = "friend")
public class Friend extends CommonEntity {
  @ManyToOne
  @JoinColumn(name = "fromUser")
  private User fromUser;

  @ManyToOne
  @JoinColumn(name = "toUser")
  private User toUser;

  @Column(name = "accepted")
  private boolean accepted;

  public User getFromUser() {
    return fromUser;
  }

  public void setFromUser(User fromUser) {
    this.fromUser = fromUser;
  }

  public User getToUser() {
    return toUser;
  }

  public void setToUser(User toUser) {
    this.toUser = toUser;
  }

  public boolean isAccepted() {
    return accepted;
  }

  public void setAccepted(boolean toAccepted) {
    this.accepted = toAccepted;
  }
}
