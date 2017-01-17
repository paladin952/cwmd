package application.core.repository;

import application.core.model.FlowPath;

import java.util.List;

/**
 * Contract for {@link FlowPathRepositoryCustom}
 */
public interface FlowPathRepositoryCustom {

    /**
     * Get all flow's paths
     */
    List<FlowPath> getAllSQL();

    /**
     * Get one flow path
     * @param flowId the id
     * @param departmentId department id
     */
    FlowPath getOneSQL(Integer flowId, Integer departmentId);

    /**
     * Get some flow paths
     * @param flowId id
     */
    List<FlowPath> getSomeSQL(Integer flowId);
}
