package ru.doberman.dao;

import java.util.List;

/**
 * @author izerenev
 *         Date: 30.01.14
 */
public interface CommonDao<T> {
  void create(T o);

  T getById(int id);

  List<T> getAll();

  void update(T o);

  void delete(int id);
}
