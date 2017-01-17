package application.core.repository;

import application.core.model.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repo for {@link User}
 */
@Transactional
public interface UserRepository extends CWMDRepository<String, User> {

}
