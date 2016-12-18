package application.core.repository;

import application.core.model.FlowPath;

import java.util.List;

public interface FlowPathRepositoryCustom {
    List<FlowPath> getAllSQL();
    FlowPath getOneSQL(Integer flowId, Integer departmentId);
    List<FlowPath> getSomeSQL(Integer flowId);
}
