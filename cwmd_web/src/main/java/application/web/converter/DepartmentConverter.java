package application.web.converter;

import application.core.model.Department;
import application.core.model.DepartmentUser;
import application.web.dto.DepartmentDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Converting {@link Department} to {@link DepartmentDTO} or the other way around
 */
@Component
public class DepartmentConverter extends Converter<Department, DepartmentDTO> {

    /**
     * Converting a {@link Department} to a {@link DepartmentDTO}
     * @param obj The department as input
     * @return a new {@link Department}
     */
    @Override
    public DepartmentDTO toDTO(Department obj) {
        if (obj == null)
            return new DepartmentDTO();
        return DepartmentDTO.builder()
                .id(obj.getId())
                .userList(obj.getUserList().stream()
                    .map(DepartmentUser::getUser)
                    .collect(Collectors.toList()))
                .name(obj.getName())
                .build();
    }

    /**
     * Converting a {@link DepartmentDTO} to a {@link Department}
     * @param departmentDTO The department dto as input
     * @return a new {@link Department}
     */
    @Override
    public Department fromDTO(DepartmentDTO departmentDTO) {
        Department tmp = Department.builder()
            .id(departmentDTO.getId())
            .name(departmentDTO.getName())
            .build();

        tmp.setUserList(departmentDTO.getUserList().stream()
        .map(user -> new DepartmentUser(tmp, user))
        .collect(Collectors.toList()));

        return tmp;
    }

    /**
     * Converting a {@link Department} to a {@link DepartmentDTO}  that has also a {@link application.core.model.User}
     * @param department The department as input
     * @return a new {@link Department}
     */
    public DepartmentDTO toDtoWithoutUsers(Department department) {
        if (department == null)
            return new DepartmentDTO();
        return DepartmentDTO.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }
}
