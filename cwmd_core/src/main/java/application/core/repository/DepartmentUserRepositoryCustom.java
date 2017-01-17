package application.core.repository;

import application.core.model.Department;
import application.core.model.DepartmentUser;
import application.core.model.User;

/**
 * DepartmentUser custom repo
 */
public interface DepartmentUserRepositoryCustom {
    /**
     * Save
     * @param deptUser a department user
     */
    void saveSQL(DepartmentUser deptUser);

    /**
     * Save
     * @param department the department
     * @param user The user
     */
    void saveSQL(Department department, User user);
}
