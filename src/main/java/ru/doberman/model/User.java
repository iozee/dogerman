package ru.doberman.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author izerenev
 *         Date: 08.12.13
 */
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
@Access(AccessType.FIELD)
public class User extends NamedCommonEntity {
  @Column(name = "password")
  private String password;

  @Column(name = "enabled")
  private boolean enabled;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_role",
      joinColumns = {@JoinColumn(name = "userId")},
      inverseJoinColumns = {@JoinColumn(name = "roleId")})
  private Set<Role> roles;

  @Column(name = "created")
  @Temporal(TemporalType.DATE)
  private Date created;

  @Column(name = "modified")
  @Temporal(TemporalType.DATE)
  private Date modified;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
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
