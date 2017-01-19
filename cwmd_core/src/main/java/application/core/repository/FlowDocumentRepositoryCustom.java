package application.core.repository;

import application.core.model.FlowDocument;

import java.util.List;

public interface FlowDocumentRepositoryCustom {
    List<FlowDocument> getSomeSQL(Integer flowId);
}
