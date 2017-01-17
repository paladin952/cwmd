package application.web.dto;

import application.core.model.Document;
import lombok.*;

import java.util.List;

/**
 * DTO for {@link application.core.model.Flow}
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class FlowDTO {
    private Integer id;
    private List<Document> flowDocuments;
    private List<DepartmentDTO> flowPath;
    private Integer crtDepartment;
    private String remarks;
}
