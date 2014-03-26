package ru.doberman.managedbean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.doberman.core.SystemUtils;
import ru.doberman.model.User;
import ru.doberman.service.UserService;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * @author izerenev
 *         Date: 20.02.14
 */
@ManagedBean
@ViewScoped
@Component
public class UserMB extends CommonMB<User, UserService> implements Serializable {
  private static final Logger LOG = LoggerFactory.getLogger(UserMB.class);

  @Resource
  public void setService(UserService service) {
    this.service = service;
  }

  @Override
  public Class<User> getClazz() {
    return User.class;
  }

  public String register() {
    try {
      service.register(getEntity());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      SystemUtils.showMessage("Ошибка", "Ошибка при регистрации");
    }
    return "login?faces-redirect=true";
  }

  public List<User> getMyFriends() {
    return service.getMyFriends();
  }
}
