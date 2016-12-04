package application.web.dto;

import application.core.model.Department;
import application.core.model.Document;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FlowStartDTO {
    private List<Document> documents;
    private List<Department> path;
}
