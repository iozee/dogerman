package ru.doberman.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.doberman.core.SystemUtils;
import ru.doberman.model.Dog;
import ru.doberman.model.User;
import ru.doberman.model.Vote;

import java.util.List;
import java.util.Random;

/**
 * @author izerenev
 *         Date: 30.01.14
 */
@Repository
public class DogDao extends CommonDaoImpl<Dog> {
  public Dog getRandomDog() {
    Criteria criteria = getCriteria();
    criteria.setProjection(Projections.rowCount());
    int count = ((Number) criteria.uniqueResult()).intValue();
    if (count != 0) {
      int index = new Random().nextInt(count);
      criteria = getCriteria();
      return (Dog) criteria.setFirstResult(index).setMaxResults(1).uniqueResult();
    }
    return null;
  }

  public int getVotes(Dog dog, boolean isVoteUp) {
    Criteria criteria = getCriteria(Vote.class);
    return ((Number) criteria.add(Restrictions.eq("dogId", dog))
        .add(Restrictions.eq("up", isVoteUp))
        .setProjection(Projections.rowCount())
        .uniqueResult()).intValue();
  }

  @Override
  protected Class<Dog> getEntityClass() {
    return Dog.class;
  }

  public List<Dog> getMyDogs(User user) {
    Criteria criteria = getCriteria();
    criteria.add(Restrictions.eq("user", user));
    return SystemUtils.listAndCast(criteria);
  }
}
