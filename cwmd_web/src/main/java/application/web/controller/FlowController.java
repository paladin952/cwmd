package application.web.controller;

import application.core.model.Flow;
import application.core.service.IFlowService;
import application.web.converter.DepartmentConverter;
import application.web.converter.FlowConverter;
import application.web.dto.DepartmentDTO;
import application.web.dto.FlowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Controller for handling request related to Flow
 */
@RequestMapping("/flow")
@RestController
public class FlowController {
    private final static String OK_MSG = "OK";

    @Autowired
    private FlowConverter flowConverter;

    @Autowired
    private DepartmentConverter departmentConverter;

    @Autowired
    private IFlowService flowService;

    /**
     * Starting a flow
     * @param documentIds The list of document's ids
     * @param departmentIds The department's ids
     * @return The flow dto
     */
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public ResponseEntity<FlowDTO> startFlow(@RequestBody List<Integer> documentIds, @RequestBody List<Integer> departmentIds) {
        try {
            return new ResponseEntity<>(flowConverter.toDTO(flowService.startFlow(documentIds, departmentIds)), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get all flows
     * @return The list of flows
     */
    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<FlowDTO>> getAll() {
        try {
            return new ResponseEntity<>(flowConverter.toDTOs(flowService.read()), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get the count of flows
     * @return a integer values
     */
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseEntity<Integer> countAll() {
        int size = flowService.read().size();
        if (size == 0) {
            return new ResponseEntity<>(size-1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(size, HttpStatus.OK);
        }
    }

    /**
     * get all active flows
     * @return the list of active flow
     */
    @Transactional
    @RequestMapping(value = "/all/active", method = RequestMethod.GET)
    public ResponseEntity<List<FlowDTO>> getActive() {
        try {
            return new ResponseEntity<>(flowConverter.toDTOs(flowService.readActive()), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * get the number of active flows
     * @return integer value
     */
    @RequestMapping(value = "/count/active", method = RequestMethod.GET)
    public ResponseEntity<Integer> countActive() {
        int size = flowService.readActive().size();
        if (size == 0) {
            return new ResponseEntity<>(size-1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(size, HttpStatus.OK);
        }
    }

    /**
     * Get all finished flows
     * @return the list of finished docs
     */
    @Transactional
    @RequestMapping(value = "/all/finished", method = RequestMethod.GET)
    public ResponseEntity<List<FlowDTO>> getFinished() {
        try {
            return new ResponseEntity<>(flowConverter.toDTOs(flowService.readFinished()), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get the number of all finished flows
     * @return Integer value
     */
    @RequestMapping(value = "/count/finished", method = RequestMethod.GET)
    public ResponseEntity<Integer> countFinished() {
        int size = flowService.readFinished().size();
        if (size == 0) {
            return new ResponseEntity<>(size-1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(size, HttpStatus.OK);
        }
    }

    /**
     * Get a specific flow
     * @param flowId The id of that doc
     * @return The document
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<FlowDTO> getOne(@PathVariable("id") final Integer flowId) {
        try {
            return new ResponseEntity<>(flowConverter.toDTO(flowService.readOne(flowId)), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Checks if a document is in the last step
     * @param flowId The id of that doc
     * @return True if yes, false otherwise
     */
    @RequestMapping(value = "/isAtEnd/{id}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> isFlowAtEnd(@PathVariable("id") final Integer flowId) {
        try {
            return new ResponseEntity<>(flowService.isFlowAtEnd(flowId), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get the current department for some id
     * @param flowId the id
     * @return Department dto
     */
    @RequestMapping(value = "/currentDepartment/{id}", method = RequestMethod.GET)
    public ResponseEntity<DepartmentDTO> getCurrentDepartmentFor(@PathVariable("id") final Integer flowId) {
        try {
            return new ResponseEntity<>(departmentConverter.toDTO(flowService.getCurrentDepartmentFor(flowId)), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Go to next department
     * @param flowId Flow's id
     * @return The new dto for that flow
     */
    @RequestMapping(value = "/goToNextDepartment/{id}", method = RequestMethod.GET)
    public ResponseEntity<FlowDTO> goToNextDepartmentFor(@PathVariable("id") final Integer flowId) {
        try {
            return new ResponseEntity<>(flowConverter.toDTO(flowService.goToNextDepartmentFor(flowId)), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get a flow based on id and a flow
     * @param flowId The id
     * @param flow The flow
     * @return The flow
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FlowDTO> update(@PathVariable("id") final Integer flowId, @RequestBody Flow flow) {
        if (!flow.getId().equals(flowId)) { // can't modify someone else's flow using your current flow
            return new ResponseEntity<>(flowConverter.toDTO(new Flow()), HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity<>(flowConverter.toDTO(flowService.update(flow)), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * GEt a flow based only on id
     * @param flowId The id
     * @return The flow
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") final Integer flowId) {
        try {
            flowService.delete(flowId);
            return new ResponseEntity<>(OK_MSG, HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
