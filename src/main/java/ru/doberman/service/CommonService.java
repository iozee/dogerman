package ru.doberman.service;

import ru.doberman.dao.CommonDaoImpl;
import ru.doberman.model.CommonEntity;

import java.util.List;

/**
 * @author izerenev
 *         Date: 12.03.14
 */
public interface CommonService<Entity extends CommonEntity, DaoImpl extends CommonDaoImpl<Entity>> {
  DaoImpl getDao();

  void setDao(DaoImpl dao);

  Entity getById(int id);

  List<Entity> getAll();
}
