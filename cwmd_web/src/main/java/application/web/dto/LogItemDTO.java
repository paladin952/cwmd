package application.web.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LogItemDTO {
    private String level;
    private String timestamp;
    private String tag;
    private String msg;
    private String user;
    private String department;
    private String documentType;
    private String exception;

}
