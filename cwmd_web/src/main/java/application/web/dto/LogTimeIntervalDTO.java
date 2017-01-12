package application.web.dto;

import lombok.*;

import java.sql.Timestamp;

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
