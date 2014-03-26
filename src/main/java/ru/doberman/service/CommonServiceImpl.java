package ru.doberman.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.doberman.dao.CommonDaoImpl;
import ru.doberman.model.CommonEntity;

import java.util.List;

/**
 * @author izerenev
 *         Date: 05.03.14
 */
@Service
@Transactional
public abstract class CommonServiceImpl<Entity extends CommonEntity, DaoImpl extends CommonDaoImpl<Entity>>
    implements CommonService<Entity, DaoImpl> {
  protected DaoImpl dao;

  public final DaoImpl getDao() {
    return dao;
  }

  public abstract void setDao(DaoImpl dao);

  public final Entity getById(int id) {
    return dao.getById(id);
  }

  public final List<Entity> getAll() {
    return dao.getAll();
  }
}
