package application.core.service;

import application.core.model.Department;
import application.core.model.Flow;
import application.core.model.User;

import java.util.List;

public interface IFlowService {
    Flow startFlow(List<Integer> documents, List<Integer> departments, String username);
    List<Flow> readAll();
    List<Flow> read(String username);
    List<Flow> read();
    List<Flow> readActive(String username);
    List<Flow> readActive();
    List<Flow> readFinished(String username);
    List<Flow> readFinished();
    Flow readOne(Integer flowId);
    Flow goToNextDepartmentFor(Integer flowId);
    Flow returnToInitialDepartmentFor(Integer flowId, String remark);
    Boolean isFlowAtEnd(Integer flowId);
    Department getCurrentDepartmentFor(Integer flowId);
    List<User> getUsersFromCurrentDepartmentFor(Integer flowId);
    List<Flow> getFlowsForDepartment(Integer departmentId);
    List<Flow> getFlowsForUser(String username);
    Flow update(Flow flow);
    void addRemarks(Integer flowId, String remarks);
    void rejectFlow(Integer flowId);
    void delete(Integer flowId);
}
