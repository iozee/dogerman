package ru.doberman.model;

import javax.persistence.*;

/**
 * @author izerenev
 *         Date: 06.03.14
 */
@Entity
@Table(name = "user_role", uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "roleId"})})
public class UserRole extends CommonEntity {
  private User user;
  private Role role;

  @ManyToOne
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @ManyToOne
  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
