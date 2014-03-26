package ru.doberman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.doberman.core.SystemConsts;
import ru.doberman.core.SystemUtils;
import ru.doberman.dao.UserDao;
import ru.doberman.model.User;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author izerenev
 *         Date: 26.02.14
 */
@Service
@Transactional
public class UserService extends CommonServiceImpl<User, UserDao> implements UserDetailsService {
  @Autowired
  public void setDao(UserDao dao) {
    this.dao = dao;
  }

  public void register(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
    user.setEnabled(true);
    user.setPassword(getEncodedPassword(user.getPassword()));
    dao.register(user);
  }

  private String getEncodedPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(password.getBytes("UTF-8"));
    byte[] digest = md.digest();
    StringBuilder hexString = new StringBuilder();
    for (byte aDigest : digest) {
      String hex = Integer.toHexString(0xff & aDigest);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    User user = dao.getUserByName(name);
    if (user != null) {
      return new UserData(user, true, true, true, dao.getUserAuthorities(user));
    }
    return null;
  }

  public boolean isLoggedIn() {
    GrantedAuthority userAuthority = new SimpleGrantedAuthority(SystemConsts.Roles.ROLE_USER);
    return getAuthorities().contains(userAuthority);
  }

  public Set<String> getRoles() {
    Set<String> roles = new HashSet<String>();
    for (GrantedAuthority authority : getAuthorities()) {
      roles.add(authority.getAuthority());
    }
    return !roles.isEmpty() ? roles : Collections.<String>emptySet();
  }

  private Collection<? extends GrantedAuthority> getAuthorities() {
    return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
  }

  public List<User> getMyFriends() {
    return dao.getFriendsByUser(SystemUtils.getCurrentUser());
  }
}
