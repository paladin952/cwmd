package application.web.dto;

import lombok.*;

/**
 * DTO for {@link application.core.utils.logging.Log}
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LogTimeIntervalDTO {
    private String from;
    private String to;
}
