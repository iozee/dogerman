package ru.doberman.core;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.doberman.model.User;
import ru.doberman.service.UserData;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * @author izerenev
 *         Date: 03.03.14
 */
public class SystemUtils {
  private static final Logger LOG = LoggerFactory.getLogger(SystemUtils.class);

  public static void showMessage(String header) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(header));
  }

  public static void showMessage(String header, String message) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(header, message));
  }

  public static <T> T getAndCast(Criteria criteria) {
    @SuppressWarnings("unchecked")
    Object result = criteria.uniqueResult();
    return result == null ? null : (T) result;
  }

  public static <T> List<T> listAndCast(Criteria criteria) {
    @SuppressWarnings("unchecked")
    List list = criteria.list();
    return list == null || list.isEmpty() ? Collections.emptyList() : list;
  }

  public static User getCurrentUser() {
    return ((UserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
  }

  private static Properties getApplicationProperties() throws IOException {
    Properties properties = new Properties();
    InputStream input = null;
    try {
      input = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/WEB-INF/classes/config/dogerman.properties");
      properties.load(input);
      return properties;
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          LOG.error(e.getMessage(), e);
        }
      }
    }
  }

  public static String getProperty(String name) throws IOException {
    return getApplicationProperties().getProperty(name);
  }

  public static String getContextPath() {
    return FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
  }
}
