package application.web.dto;

import application.core.model.User;
import lombok.*;

import java.util.List;

/**
 * DTO for {@link application.core.model.Department}
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DepartmentDTO {
    private Integer id;
    private List<User> userList;
    private String name;
}
