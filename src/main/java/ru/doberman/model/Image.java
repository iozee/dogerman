package ru.doberman.model;

import javax.persistence.*;

/**
 * @author izerenev
 *         Date: 05.03.14
 */
@Entity
@Table(name = "image")
@Inheritance(strategy = InheritanceType.JOINED)
@Access(AccessType.FIELD)
public class Image extends CommonEntity  {
  @Basic
  @Column(name = "path")
  private String path;

  @Basic
  @Column(name = "thumb")
  private String thumb;

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getThumb() {
    return thumb;
  }

  public void setThumb(String thumb) {
    this.thumb = thumb;
  }
}
