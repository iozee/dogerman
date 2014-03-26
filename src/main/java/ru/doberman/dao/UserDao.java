package ru.doberman.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import ru.doberman.core.SystemUtils;
import ru.doberman.model.Friend;
import ru.doberman.model.Role;
import ru.doberman.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author izerenev
 *         Date: 26.02.14
 */
@Repository
public class UserDao extends CommonDaoImpl<User> {
  public void register(User user) {
    create(user);
  }

  public User getUserByName(String name) {
    Criteria criteria = getCriteria();
    criteria.add(Restrictions.eq("name", name))
        .setMaxResults(1);
    List<User> userList = SystemUtils.listAndCast(criteria);
    if (!userList.isEmpty()) {
      return userList.get(0);
    }
    return null;
  }

  public Set<GrantedAuthority> getUserAuthorities(User user) {
    Set<Role> roles = user.getRoles();
    Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(roles.size());
    for (Role role : roles){
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    }
    return authorities;
  }

  @Override
  protected Class<User> getEntityClass() {
    return User.class;
  }

  public List<User> getFriendsByUser(User user) {
    Criteria criteria = getCriteria(Friend.class).add(Restrictions.and(
            Restrictions.or(
                Restrictions.eq("fromUser", user),
                Restrictions.eq("toUser", user)
            ), Restrictions.eq("accepted", true)
        )
    );
    return SystemUtils.listAndCast(criteria);
  }
}
