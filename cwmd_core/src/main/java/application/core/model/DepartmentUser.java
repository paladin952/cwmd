package application.core.model;

import application.core.model.PKs.DepartmentUserPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "department_user")
@IdClass(DepartmentUserPK.class)
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DepartmentUser {
    @JsonIgnore
    @Id
    @ManyToOne
    @JoinColumn(name = "DepartmentID", nullable = false)
    private Department department;

    @Id
    @ManyToOne
    @JoinColumn(name = "Username", nullable = false, unique = true)
    private User user;

    @Override
    public String toString() {
        return "DepartmentUser{" +
                "user=" + user +
                '}';
    }
}
