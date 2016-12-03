package application.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_details")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EntryID")
    private Integer id;

    @Column(name = "FirstName", length = 60, nullable = false)
    private String firstName;

    @Column(name = "LastName", length = 60, nullable = false)
    private String lastName;

    @Column(name = "Address")
    private String address;

    @Column(name = "Email", length = 60)
    private String email;

    @Column(name = "PhoneNumber")
    private Long phoneNumber;

    @Column(name = "IsDepartmentChief", nullable = false)
    private Boolean isDepartmentChief;

    @Override
    public String toString() {
        return "first name: " + firstName +
                ", last name: " +
                lastName +
                ", address: " +
                address +
                ", e-mail: " +
                email +
                ", phone no.: " +
                phoneNumber +
                ", is chief of department: " +
                isDepartmentChief;
    }
}
