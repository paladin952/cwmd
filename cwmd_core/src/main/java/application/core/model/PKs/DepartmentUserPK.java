package application.core.model.PKs;

import application.core.model.Department;
import application.core.model.User;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class DepartmentUserPK implements Serializable {
    private Department department;
    private User user;
}
