package application.core.repository;

import application.core.model.DepartmentUser;
import application.core.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface DepartmentUserRepository extends CWMDRepository<Integer, DepartmentUser> {
    List<DepartmentUser> findByUser(User user);
}
