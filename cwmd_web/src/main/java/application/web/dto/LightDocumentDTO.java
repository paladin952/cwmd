package application.web.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class LightDocumentDTO {
    private Integer id;
    private String name;
    private Date dateAdded;
    private String username;
}
