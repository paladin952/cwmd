package application.web.dto;

import lombok.*;

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
