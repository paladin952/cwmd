package application.web.dto;

import lombok.*;

/**
 * DTO for filtering data of a log
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class LogFilterDataDTO {
    private String filter;
    private LogTimeIntervalDTO interval;
}
