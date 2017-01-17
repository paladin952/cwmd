package application.web.dto;

import lombok.*;


/**
 * Dto for {@link application.core.model.User}
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserDto {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private long phoneNumber;
    private Boolean departmentChief;
    private String department;
    private String role;
}
