package application.core.repository;

import application.core.model.DepartmentUser;
import application.core.model.PKs.DepartmentUserPK;
import application.core.model.User;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Repo for {@link DepartmentUser}
 */
@Transactional
public interface DepartmentUserRepository extends CWMDRepository<DepartmentUserPK, DepartmentUser>, DepartmentUserRepositoryCustom {
    List<DepartmentUser> findByUser(User user);
}
