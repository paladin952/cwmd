package application.core.model.dr;

import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "drPersonInfo")
public class DRPersonInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drPersonInfoId")
    private Integer drPersonInfoId;

    @Column(name = "fullName")
    @NotNull
    private String fullName;

    @Column(name = "title")
    private String title;

    @Column(name = "department")
    @NotNull
    private String department;

    @Column(name = "phoneNumber")
    @NotNull
    private String phoneNumber;

    @Column(name = "email")
    @NotNull @Email
    private String email;
}
