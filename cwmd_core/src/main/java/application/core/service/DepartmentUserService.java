package application.core.service;

import application.core.model.Department;
import application.core.model.DepartmentUser;
import application.core.model.User;
import application.core.repository.DepartmentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentUserService {
    @Autowired
    private DepartmentUserRepository departmentUserRepository;

    public Department getUserDepartment(User user) {
        List<DepartmentUser> departmentUsers = departmentUserRepository.findByUser(user);
        if (departmentUsers.size() > 0) {
            return departmentUsers.get(0).getDepartment();
        }

        return null;
    }

    public String getUserDepartmentName(User user) {
        Department department = this.getUserDepartment(user);
        String departmentName = "";
        if (department != null) {
            departmentName = department.getName();
        }
        return departmentName;
    }

    public DepartmentUser addDepartmentUser(Department department, User user) {
        DepartmentUser departmentUser = new DepartmentUser();
        departmentUser.setDepartment(department);
        departmentUser.setUser(user);
        return departmentUserRepository.save(departmentUser);
    }
}
