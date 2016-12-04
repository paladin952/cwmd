package application.core.service;

import application.core.model.Department;
import application.core.model.Document;
import application.core.model.Flow;

import java.util.List;

public interface IFlowService {
    Flow startFlow(List<Document> documents, List<Department> departments);
    List<Flow> read();
    Flow readOne(Integer flowId);
    Flow goToNextDepartmentFor(Integer flowId);
    Flow returnToInitialDepartmentFor(Integer flowId, String remark);
    Boolean isFlowAtEnd(Integer flowId);
    Department getCurrentDepartmentFor(Integer flowId);
    Flow update(Flow flow);
    IFlowService delete(Integer flowId);
}
