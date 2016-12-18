package application.core.repository;

import application.core.model.Department;

import java.util.List;

public interface DepartmentRepositoryCustom {
    List<Department> getAllSQL();
    Department getOneSQL(Integer id);
}
