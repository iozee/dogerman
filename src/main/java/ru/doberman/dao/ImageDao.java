package ru.doberman.dao;

import org.springframework.stereotype.Repository;
import ru.doberman.model.DogImage;
import ru.doberman.model.Image;

/**
 * @author izerenev
 *         Date: 19.03.2014
 */
@Repository
public class ImageDao extends CommonDaoImpl<Image> {
  @Override
  protected Class<Image> getEntityClass() {
    return Image.class;
  }
}
