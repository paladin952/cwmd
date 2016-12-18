package application.web.converter;

import application.core.model.Flow;
import application.core.model.FlowDocument;
import application.core.model.FlowPath;
import application.web.dto.FlowDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class FlowConverter extends Converter<Flow, FlowDTO> {
    @Autowired
    private DepartmentConverter departmentConverter;

    @Override
    public FlowDTO toDTO(Flow obj) {
        return FlowDTO.builder()
                .id(obj.getId())
                .flowDocuments(obj.getFlowDocuments().stream()
                    .map(FlowDocument::getDocument)
                    .collect(Collectors.toList()))
                .flowPath(obj.getFlowPath().stream()
                    .map(flowPath -> departmentConverter.toDTO(flowPath.getDepartment()))
                    .collect(Collectors.toList()))
                .crtDepartment(obj.getCrtDepartment())
                .remarks(obj.getRemarks())
                .build();
    }

    @Override
    public Flow fromDTO(FlowDTO flowDTO) {
        Flow tmp = Flow.builder()
                .id(flowDTO.getId())
                .remarks(flowDTO.getRemarks())
                .crtDepartment(flowDTO.getCrtDepartment())
                .build();

        tmp.setFlowDocuments(flowDTO.getFlowDocuments().stream()
            .map(document -> new FlowDocument(tmp, document))
            .collect(Collectors.toList()));

        tmp.setFlowPath(flowDTO.getFlowPath().stream()
            .map(department -> new FlowPath(tmp, departmentConverter.fromDTO(department)))
            .collect(Collectors.toList()));

        return tmp;
    }
}
