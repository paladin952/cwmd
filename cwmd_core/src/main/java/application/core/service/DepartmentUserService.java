package application.core.service;

import application.core.model.Department;
import application.core.model.DepartmentUser;
import application.core.model.User;
import application.core.repository.DepartmentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service that handles Departments for Users.
 */
@Service
@Transactional
public class DepartmentUserService {
    @Autowired
    private DepartmentUserRepository departmentUserRepository;

    /**
     * Returns the {@link Department} for a {@link User}.
     * @param user The user.
     * @return The user's department.
     */
    public Department getUserDepartment(User user) {
        List<DepartmentUser> departmentUsers = departmentUserRepository.findByUser(user);
        if (departmentUsers.size() > 0) {
            return departmentUsers.get(0).getDepartment();
        }

        return null;
    }

    /**
     * Returns the name of a {@link User}'s {@link Department}
     * @param user The user.
     * @return The name of the user's department.
     */
    public String getUserDepartmentName(User user) {
        Department department = this.getUserDepartment(user);
        String departmentName = "";
        if (department != null) {
            departmentName = department.getName();
        }
        return departmentName;
    }

    /**
     * Adds a user to a Department.
     * @param department The departmen where the used is added.
     * @param user The user to be added.
     */
    public void addDepartmentUser(Department department, User user) {
        departmentUserRepository.saveSQL(department, user);
        //return departmentUserRepository.save(departmentUser);
    }
}
