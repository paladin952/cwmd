package application.core.repository;

import application.core.model.Department;
import application.core.model.DepartmentUser;
import application.core.model.User;

public interface DepartmentUserRepositoryCustom {
    void saveSQL(DepartmentUser deptUser);
    void saveSQL(Department department, User user);
}
