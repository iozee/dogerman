package ru.doberman.dao;

import org.springframework.stereotype.Repository;
import ru.doberman.model.DogImage;

/**
 * @author izerenev
 *         Date: 19.03.2014
 */
@Repository
public class DogImageDao extends CommonDaoImpl<DogImage> {
  @Override
  protected Class<DogImage> getEntityClass() {
    return DogImage.class;
  }
}
