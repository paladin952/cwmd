package application.web.converter;

import application.core.model.Flow;
import application.core.model.FlowDocument;
import application.core.model.FlowPath;
import application.web.dto.FlowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class FlowConverter extends Converter<Flow, FlowDTO> {
    @Autowired
    private DepartmentConverter departmentConverter;

    @Autowired
    private LightDocumentConverter documentConverter;

    @Override
    public FlowDTO toDTO(Flow obj) {
        return FlowDTO.builder()
                .id(obj.getId())
                .flowDocuments(obj.getFlowDocuments().stream()
                    .map(flowDocument -> documentConverter.toDTO(flowDocument.getDocument()))
                    .collect(Collectors.toList()))
                .flowPath(obj.getFlowPath().stream()
                    .map(flowPath -> departmentConverter.toDtoWithoutUsers(flowPath.getDepartment()))
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
            .map(documentDTO -> new FlowDocument(tmp, documentConverter.fromDTO(documentDTO)))
            .collect(Collectors.toList()));

        tmp.setFlowPath(flowDTO.getFlowPath().stream()
            .map(department -> new FlowPath(tmp, departmentConverter.fromDTO(department)))
            .collect(Collectors.toList()));

        return tmp;
    }
}
