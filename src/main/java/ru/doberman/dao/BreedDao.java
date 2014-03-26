package ru.doberman.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.doberman.core.SystemUtils;
import ru.doberman.model.Breed;

import java.util.List;

/**
 * @author izerenev
 *         Date: 17.03.2014
 */
@Repository
public class BreedDao extends CommonDaoImpl<Breed> {
  @Override
  protected Class<Breed> getEntityClass() {
    return Breed.class;
  }

  public Breed getBreedByName(String name) {
    Criteria criteria = getCriteria();
    criteria.add(Restrictions.eq("name", name));
    return SystemUtils.getAndCast(criteria);
  }

  public List<Breed> getBreedsByName(String name) {
    Criteria criteria = getCriteria();
    criteria.add(Restrictions.ilike("name", "%" + name + "%"));
    return SystemUtils.listAndCast(criteria);
  }
}
