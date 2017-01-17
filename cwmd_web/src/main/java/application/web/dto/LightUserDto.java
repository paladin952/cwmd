package application.web.dto;

import lombok.*;

/**
 * DTO for a light {@link application.core.model.User} that has only some fields
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class LightUserDto {
    private String username;

    private String role;
}
