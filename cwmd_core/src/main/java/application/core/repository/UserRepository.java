package application.core.repository;

import application.core.model.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends CWMDRepository<String, User> {

}
