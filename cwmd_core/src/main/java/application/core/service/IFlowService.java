package application.core.service;

import application.core.model.Department;
import application.core.model.Flow;

import java.util.List;

/**
 * Interface for the Flow Sevice.
 */
public interface IFlowService {

    /**
     * Starts a new Flow with the given documents for the given departments.
     * @param documents The documents in the flow.
     * @param departments The departments in the flow.
     * @return the {@link Flow} created.
     */
    Flow startFlow(List<Integer> documents, List<Integer> departments);

    /**
     * Returns all the flows.
     * @return A list of {@link Flow}.
     */
    List<Flow> read();

    /**
     * Returns all the active flows.
     * @return A list of active {@link Flow}s.
     */
    List<Flow> readActive();

    /**
     * Returns all the finished flows.
     * @return A list of finished {@link Flow}s.
     */
    List<Flow> readFinished();

    /**
     * Returns a Flow with the given ID.
     * @param flowId the ID of the flow.
     * @return The Flow with the given ID.
     */
    Flow readOne(Integer flowId);

    /**
     * Goes to the next Department in the {@link Flow}
     * @param flowId The ID of the flow.
     * @return The new {@link Flow}.
     */
    Flow goToNextDepartmentFor(Integer flowId);

    /**
     * Returns to the initial department for a {@link Flow} with a remark
     * @param flowId The ID of the flow
     * @param remark THe Remark of the return operation.
     * @return The new {@link Flow}
     */
    Flow returnToInitialDepartmentFor(Integer flowId, String remark);

    /**
     * Checks if a Flow is at the end
     * @param flowId The ID of the flow.
     * @return True if the {@link Flow} is at the end, False otherwise.
     */
    Boolean isFlowAtEnd(Integer flowId);

    /**
     * Returns the current Department for a {@link Flow}
     * @param flowId The ID of the {@link Flow}.
     * @return The current {@link Department} for the flow.
     */
    Department getCurrentDepartmentFor(Integer flowId);

    /**
     * Updates a flow from a flow.
     * @param flow The updated flow
     * @return The new {@link Flow}
     */
    Flow update(Flow flow);

    /**
     * Deletes a Flow with a given ID
     * @param flowId the ID of the flow.
     */
    void delete(Integer flowId);
}
