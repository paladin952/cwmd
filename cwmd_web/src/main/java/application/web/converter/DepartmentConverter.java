package application.web.converter;

import application.core.model.Department;
import application.core.model.DepartmentUser;
import application.web.dto.DepartmentDTO;

import java.util.stream.Collectors;

public class DepartmentConverter extends Converter<Department, DepartmentDTO> {
    @Override
    public DepartmentDTO toDTO(Department obj) {
        return DepartmentDTO.builder()
                .id(obj.getId())
                .userList(obj.getUserList().stream()
                    .map(DepartmentUser::getUser)
                    .collect(Collectors.toList()))
                .name(obj.getName())
                .build();
    }

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
}