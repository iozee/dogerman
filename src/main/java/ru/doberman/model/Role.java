package ru.doberman.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import org.hibernate.annotations.Cache;
import javax.persistence.*;


/**
 * @author izerenev
 *         Date: 06.03.14
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Role extends NamedCommonEntity {
}
