package ru.doberman.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author izerenev
 *         Date: 04.02.14
 */
@MappedSuperclass
public abstract class CommonEntity implements Serializable {
  private int id;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  public final int getId() {
    return id;
  }

  public final void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "@" + id;
  }
}
