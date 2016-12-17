package application.web.controller;

import application.core.model.Department;
import application.core.model.Flow;
import application.core.repository.DepartmentRepository;
import application.core.service.IFlowService;
import application.web.converter.Converter;
import application.web.converter.DepartmentConverter;
import application.web.converter.FlowConverter;
import application.web.dto.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    private IFlowService flowService;

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public ResponseEntity<FlowDTO> startFlow(@RequestBody FlowStartDTO flowStartDTO) {
        try {
            return new ResponseEntity<>(flowConverter.toDTO(flowService.startFlow(flowStartDTO.getDocuments(), flowStartDTO.getPath())), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(flowConverter.toDTO(new Flow()), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<FlowDTO>> getAll() {
        try {
            return new ResponseEntity<>(flowConverter.toDTOs(flowService.read()), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(flowConverter.toDTOs(new ArrayList<Flow>()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<FlowDTO> getOne(@PathVariable("id") final Integer flowId) {
        try {
            return new ResponseEntity<>(flowConverter.toDTO(flowService.readOne(flowId)), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(flowConverter.toDTO(new Flow()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}/isAtEnd", method = RequestMethod.GET)
    public ResponseEntity<Boolean> isFlowAtEnd(@PathVariable("id") final Integer flowId) {
        try {
            return new ResponseEntity<>(flowService.isFlowAtEnd(flowId), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}/currentDepartment", method = RequestMethod.GET)
    public ResponseEntity<DepartmentDTO> getCurrentDepartmentFor(@PathVariable("id") final Integer flowId) {
        try {
            return new ResponseEntity<>(departmentConverter.toDTO(flowService.getCurrentDepartmentFor(flowId)), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(departmentConverter.toDTO(new Department()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FlowDTO> update(@PathVariable("id") final Integer flowId, @RequestBody Flow flow) {
        if (!flow.getId().equals(flowId)) // can't modify someone else's flow using your current flow
            return new ResponseEntity<>(flowConverter.toDTO(new Flow()), HttpStatus.BAD_REQUEST);

        try {
            return new ResponseEntity<>(flowConverter.toDTO(flowService.update(flow)), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(flowConverter.toDTO(new Flow()), HttpStatus.BAD_REQUEST);
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
