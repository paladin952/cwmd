package application.core.service;

import application.core.model.Department;
import application.core.model.Flow;
import application.core.model.User;

import java.util.List;

public interface IFlowService {
    Flow startFlow(List<Integer> documents, List<Integer> departments);
    List<Flow> read();
    List<Flow> readActive();
    List<Flow> readFinished();
    Flow readOne(Integer flowId);
    Flow goToNextDepartmentFor(Integer flowId);
    Flow returnToInitialDepartmentFor(Integer flowId, String remark);
    Boolean isFlowAtEnd(Integer flowId);
    Department getCurrentDepartmentFor(Integer flowId);
    List<User> getUsersFromCurrentDepartmentFor(Integer flowId);
    Flow update(Flow flow);
    void addRemarks(Integer flowId, String remarks);
    void delete(Integer flowId);
}
