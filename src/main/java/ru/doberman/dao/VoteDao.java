package ru.doberman.dao;

import org.springframework.stereotype.Repository;
import ru.doberman.model.Vote;

/**
 * @author izerenev
 *         Date: 16.02.14
 */
@Repository
public class VoteDao extends CommonDaoImpl<Vote> {
  @Override
  protected Class<Vote> getEntityClass() {
    return Vote.class;
  }
}
