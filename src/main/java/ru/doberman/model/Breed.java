package ru.doberman.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

/**
 * @author izerenev
 *         Date: 09.02.14
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Breed extends NamedCommonEntity implements Serializable {
}
