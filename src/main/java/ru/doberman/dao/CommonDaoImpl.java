package ru.doberman.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.doberman.core.SystemUtils;
import ru.doberman.model.CommonEntity;

import java.util.List;

/**
 * @author izerenev
 *         Date: 04.02.14
 */
@Repository
public abstract class CommonDaoImpl<Entity extends CommonEntity> implements CommonDao<Entity> {
  @Autowired
  private SessionFactory sessionFactory;

  protected abstract Class<Entity> getEntityClass();

  @Override
  public void create(Entity e) {
    getSession().save(e);
  }

  @Override
  public Entity getById(int id) {
    return (Entity) getSession().get(getEntityClass(), id);
  }

  @Override
  public List<Entity> getAll() {
    return SystemUtils.listAndCast(getSession().createCriteria(getEntityClass()).setCacheable(true));
  }

  @Override
  public void update(Entity e) {
    getSession().update(e);
  }

  @Override
  public void delete(int id) {
    getSession().delete(getById(id));
  }

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  protected Criteria getCriteria() {
    return getSession().createCriteria(getEntityClass());
  }

  protected Criteria getCriteria(Class<? extends CommonEntity> clazz) {
    return getSession().createCriteria(clazz);
  }

  public void evict(Entity e) {
    getSession().evict(e);
  }
}
