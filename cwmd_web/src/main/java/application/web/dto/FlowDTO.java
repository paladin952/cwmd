package application.web.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class FlowDTO {
    private Integer id;
    private List<LightDocumentDTO> flowDocuments;
    private List<DepartmentDTO> flowPath;
    private Integer crtDepartment;
    private String remarks;
}
