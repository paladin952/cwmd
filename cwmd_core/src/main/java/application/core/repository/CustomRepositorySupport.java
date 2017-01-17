package application.core.repository;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Base class for custom repository
 */
@Getter
@Setter
public abstract class CustomRepositorySupport<ID, T> {

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<T> domainClass;

    public CustomRepositorySupport(Class<T> domainClass) {
        this.domainClass = domainClass;
    }
}
