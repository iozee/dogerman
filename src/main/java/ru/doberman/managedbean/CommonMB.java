package ru.doberman.managedbean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.doberman.model.CommonEntity;
import ru.doberman.service.CommonService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * @author izerenev
 *         Date: 05.03.14
 */
@ManagedBean
@ViewScoped
@Component
public abstract class CommonMB<Entity extends CommonEntity, Service extends CommonService> {
  private static final Logger LOG = LoggerFactory.getLogger(CommonMB.class);
  protected Service service;
  private Entity entity;

  protected CommonMB() {
    try {
      entity = getClazz().newInstance();
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
  }

  public Service getService() {
    return service;
  }

  public abstract void setService(Service service);

  public abstract Class<Entity> getClazz();

  public Entity getEntity() {
    return entity;
  }

  public void setEntity(Entity entity) {
    this.entity = entity;
  }

  public void resetEntity() {
    try {
      setEntity(getClazz().newInstance());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
  }

  public List<Entity> getAll() {
    return service.getAll();
  }
}
