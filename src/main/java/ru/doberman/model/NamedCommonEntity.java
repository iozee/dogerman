package ru.doberman.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author izerenev
 *         Date: 17.02.14
 */
@MappedSuperclass
public abstract class NamedCommonEntity extends CommonEntity implements Serializable {
  private String name;

  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name != null && !name.equals("") ? name : super.toString();
  }
}
