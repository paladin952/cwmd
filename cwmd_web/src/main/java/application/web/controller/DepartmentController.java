package application.web.controller;

import application.core.model.Department;
import application.core.service.DepartmentService;
import application.web.converter.DepartmentConverter;
import application.web.dto.DepartmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST Controller for {@link Department}
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentConverter departmentConverter;

    /**
     * Returns a list containing all the departments.
     * @return a list of {@link DepartmentDTO}
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<DepartmentDTO> getDepartments() {
        List<Department> departmentList = departmentService.getdepartments();
        List<DepartmentDTO> departmentDTOs = departmentList.stream()
                .map(department -> departmentConverter.toDtoWithoutUsers(department))
                .collect(Collectors.toList());
        return departmentDTOs;
    }
}
