package application.core.repository;

import application.core.model.Department;

import java.util.List;

public interface DepartmentRepositoryCustom {

    /**
     * Get all departments
     */
    List<Department> getAllSQL();

    /**
     * Get on department based on
     * @param id id
     * @return the department
     */
    Department getOneSQL(Integer id);
}
