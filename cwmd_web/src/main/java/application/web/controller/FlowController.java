package application.web.controller;

import application.core.model.Flow;
import application.core.service.IFlowService;
import application.web.converter.DepartmentConverter;
import application.web.converter.FlowConverter;
import application.web.converter.LightUserConverter;
import application.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RequestMapping("/flow")
@RestController
public class FlowController {
    private final static String OK_MSG = "OK";

    @Autowired
    private FlowConverter flowConverter;

    @Autowired
    private DepartmentConverter departmentConverter;

    @Autowired
    private LightUserConverter lightUserConverter;

    @Autowired
    private IFlowService flowService;

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public ResponseEntity<FlowDTO> startFlow(@RequestBody FlowStartDTO flowStartDTO) {
        try {
            return new ResponseEntity<>(flowConverter.toDTO(flowService.startFlow(flowStartDTO.getDocumentIds(), flowStartDTO.getDepartmentIds())), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/remarks/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> addRemarks(@PathVariable("id") Integer flowId, @RequestBody FlowRemarksDTO flowRemarksDTO) {
        try {
            flowService.addRemarks(flowId, flowRemarksDTO.getRemarks());
            return new ResponseEntity<>(OK_MSG, HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<LightUserDto>> getUsersFor(@PathVariable("id") Integer flowId) {
        try {
            return new ResponseEntity<>(lightUserConverter.toDTOs(flowService.getUsersFromCurrentDepartmentFor(flowId)), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

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

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseEntity<Integer> countAll() {
        int size = flowService.read().size();
        if (size == 0) {
            return new ResponseEntity<>(size-1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(size, HttpStatus.OK);
        }
    }

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

    @RequestMapping(value = "/count/active", method = RequestMethod.GET)
    public ResponseEntity<Integer> countActive() {
        int size = flowService.readActive().size();
        if (size == 0) {
            return new ResponseEntity<>(size-1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(size, HttpStatus.OK);
        }
    }

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

    @RequestMapping(value = "/count/finished", method = RequestMethod.GET)
    public ResponseEntity<Integer> countFinished() {
        int size = flowService.readFinished().size();
        if (size == 0) {
            return new ResponseEntity<>(size-1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(size, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<FlowDTO> getOne(@PathVariable("id") final Integer flowId) {
        try {
            return new ResponseEntity<>(flowConverter.toDTO(flowService.readOne(flowId)), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/isAtEnd/{id}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> isFlowAtEnd(@PathVariable("id") final Integer flowId) {
        try {
            return new ResponseEntity<>(flowService.isFlowAtEnd(flowId), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/currentDepartment/{id}", method = RequestMethod.GET)
    public ResponseEntity<DepartmentDTO> getCurrentDepartmentFor(@PathVariable("id") final Integer flowId) {
        try {
            return new ResponseEntity<>(departmentConverter.toDTO(flowService.getCurrentDepartmentFor(flowId)), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/goToNextDepartment/{id}", method = RequestMethod.GET)
    public ResponseEntity<FlowDTO> goToNextDepartmentFor(@PathVariable("id") final Integer flowId) {
        try {
            return new ResponseEntity<>(flowConverter.toDTO(flowService.goToNextDepartmentFor(flowId)), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FlowDTO> update(@PathVariable("id") final Integer flowId, @RequestBody FlowDTO flow) {
        if (!flow.getId().equals(flowId)) { // can't modify someone else's flow using your current flow
            return new ResponseEntity<>(flowConverter.toDTO(new Flow()), HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity<>(flowConverter.toDTO(flowService.update(flowConverter.fromDTO(flow))), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

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
