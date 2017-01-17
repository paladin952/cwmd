package application.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Base class for custom repo
 */
@NoRepositoryBean
@Transactional
public interface CWMDRepository<ID extends Serializable, T extends Serializable> extends JpaRepository<T, ID> {}
