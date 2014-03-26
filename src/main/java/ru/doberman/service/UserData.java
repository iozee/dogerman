package ru.doberman.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Class representing User entity
 *
 * @author izerenev
 *         Date: 28.02.14
 */
public class UserData extends User {
  private ru.doberman.model.User user;

  public UserData(ru.doberman.model.User user, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
    super(user.getName(), user.getPassword(), user.isEnabled(), accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    this.user = user;
  }

  public ru.doberman.model.User getUser() {
    return user;
  }
}
