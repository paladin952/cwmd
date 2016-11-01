package application.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.persistence.FetchType;
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

    // Could do: change to FetchType.LAZY
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Username")
    private User user;

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
        StringBuilder sb = new StringBuilder("first name: ");
        return sb.append(firstName)
                .append(", last name: ")
                .append(lastName)
                .append(", address: ")
                .append(address)
                .append(", e-mail: ")
                .append(email)
                .append(", phone no.: ")
                .append(phoneNumber)
                .append(", is chief of department: ")
                .append(isDepartmentChief)
                .toString();
    }
}
