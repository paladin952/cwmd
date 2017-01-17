package application.core.repository;

import application.core.model.Department;

import javax.transaction.Transactional;

/**
 * Repository for {@link Department}
 */
@Transactional
public interface DepartmentRepository extends CWMDRepository<Integer, Department>, DepartmentRepositoryCustom {

    Department findByName(String name);
}
