package application.core.service;

import application.core.model.Department;
import application.core.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for {@link Department}
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * Returns the {@link Department} with the given name
     * @param name The name of the department.
     * @return The Department with the given name
     */
    public Department getDepartmentByName(String name) {
        return departmentRepository.findByName(name);
    }

    /**
     * Returns all the departments.
     * @return A list of all the departments.
     */
    public List<Department> getdepartments() {
        return departmentRepository.findAll();
    }
}
