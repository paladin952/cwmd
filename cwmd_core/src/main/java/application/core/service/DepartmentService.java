package application.core.service;

import application.core.model.Department;
import application.core.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department getDepartmentByName(String name) {
        return departmentRepository.findByName(name);
    }

    public List<Department> getdepartments() {
        return departmentRepository.findAll();
    }
}
