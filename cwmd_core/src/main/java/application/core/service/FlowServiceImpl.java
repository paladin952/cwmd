package application.core.service;

import application.core.model.*;
import application.core.model.PKs.FlowDocumentPK;
import application.core.repository.*;
import application.core.service.exceptions.ServiceException;
import application.core.utils.FlowMailer;
import application.core.utils.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FlowServiceImpl implements IFlowService {
    private final static String TAG = FlowServiceImpl.class.getSimpleName();
    private final String VELOCITY_FLOW_AT_DEPARTMENT_TEMPLATE_LOC = "/velocity/flow_at_dept_template.vm";
    private final String VELOCITY_FLOW_REJECTED_TEMPLATE_LOC      = "/velocity/flow_rejected_template.vm";

    private final FlowRepository flowRepo;
    private final FlowPathRepository flowPathRepo;
    private final FlowDocumentRepository flowDocumentRepo;
    private final DocumentRepository documentRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final DepartmentUserRepository departmentUserRepository;
    private final FlowMailer flowMailer;
    private Log log;

    @Autowired
    public FlowServiceImpl(FlowRepository flowRepo, FlowPathRepository flowPathRepo, FlowDocumentRepository flowDocumentRepo, DocumentRepository documentRepository, DepartmentRepository departmentRepository, UserRepository userRepository, DepartmentUserRepository departmentUserRepository, FlowMailer flowMailer, Log log) {
        this.flowRepo = flowRepo;
        this.flowPathRepo = flowPathRepo;
        this.flowDocumentRepo = flowDocumentRepo;
        this.documentRepository = documentRepository;
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
        this.departmentUserRepository = departmentUserRepository;
        this.flowMailer = flowMailer;
        this.log = log;
    }

    @Override
    public Flow startFlow(List<Integer> documents, List<Integer> departments, String username) {
        User user = userRepository.findOne(username);
        log.info(TAG, "Starting a new flow for user", username);
        try {
            Flow flow = Flow.builder()
                    .crtDepartment(0)
                    .user(user)
                    .remarks("")
                    .flowPath(new ArrayList<>())
                    .flowDocuments(new ArrayList<>())
                    .build();

            flow = flowRepo.saveAndFlush(flow);
            for (Integer documentId : documents) {
                Document dbDoc = documentRepository.findOne(documentId);
                if (dbDoc != null) {
                    FlowDocument tmp = FlowDocument.builder()
                            .flow(flow)
                            .document(dbDoc)
                            .build();
                    flow.addFlowDocument(tmp);
                } else throw new RuntimeException("Document with id " + documentId + " was not found in the database");
            }
            for (Integer departmentId : departments) {
                Department dbDept = departmentRepository.getOneSQL(departmentId);
                if (dbDept != null) {
                    FlowPath tmp = FlowPath.builder()
                            .flow(flow)
                            .department(dbDept)
                            .build();
                    flow.addFlowDepartment(tmp);
                } else throw new RuntimeException("Department with id " + departmentId + " was not found in the database");
            }

            for (Integer documentId : documents) {
                Document dbDoc = documentRepository.findOne(documentId);
                if (dbDoc != null) {
                    dbDoc.setIsPartOfFlow(true);
                } else throw new RuntimeException("Document with id " + documentId + " was not found in the database");
            }

            if (flow.getFlowPath().size() > 0) {
                flowMailer.setVelocityTemplateLocation(VELOCITY_FLOW_AT_DEPARTMENT_TEMPLATE_LOC);
                flowMailer.sendMail(flow);
            }

            return flow;
        } catch (Exception e) {
            throw new ServiceException("Unknown error while starting a flow", e);
        }
    }

    @Override
    public List<Flow> read(String username) {
        log.info(TAG, "Retrieving list of flows for user", username);
        return flowRepo.findByUser_Username(username);
    }

    @Override
    public List<Flow> readAll() {
        log.info(TAG, "Retrieving list of flows");
        return flowRepo.getAllSQL();
    }

    @Override
    public List<Flow> readActive(String username) {
        return flowRepo.findByUser_Username(username)
                .stream()
                .filter(flow -> flow.getCrtDepartment() < flow.getFlowPath().size())
                .collect(Collectors.toList());
    }

    public List<Flow> read() {
        return flowRepo.getAllSQL();
    }

    public List<Flow> readActive() {
        log.info(TAG, "Retrieving list of active flows");
        return read()
                .stream()
                .filter(flow -> flow.getCrtDepartment() < flow.getFlowPath().size())
                .collect(Collectors.toList());
    }

    @Override
    public List<Flow> readFinished(String username) {
        return flowRepo.findByUser_Username(username)
                .stream()
                .filter(flow -> flow.getCrtDepartment() >= flow.getFlowPath().size())
                .collect(Collectors.toList());
    }

    public List<Flow> readFinished() {
        log.info(TAG, "Retrieving list of finished flows");
        return read()
                .stream()
                .filter(flow -> flow.getCrtDepartment() >= flow.getFlowPath().size())
                .collect(Collectors.toList());
    }

    @Override
    public Flow readOne(Integer flowId) {
        return flowRepo.getOneSQL(flowId);
    }

    @Override
    public Flow goToNextDepartmentFor(Integer flowId) {
        Flow flow = flowRepo.findOne(flowId);
        if (flow == null)
            throw new ServiceException("No flow having ID " + flowId.toString() + " was found in the database");

        log.info(TAG, "Moving to the next department for flow ID " + flowId);
        int crt = flow.getCrtDepartment();
        flow.setCrtDepartment(crt >= flow.getFlowPath().size() ? crt : crt + 1); // stop going further once we're at the end of the road
        flowRepo.save(flow);

        if (crt < flow.getFlowPath().size()) {
            try {
                flowMailer.setVelocityTemplateLocation(VELOCITY_FLOW_AT_DEPARTMENT_TEMPLATE_LOC);
                flowMailer.sendMail(flow);
            } catch (RuntimeException e) {
                e.printStackTrace();
                log.error(FlowServiceImpl.class.getSimpleName(), "Failed to send email for 'flow at department", flow.getUser().getUsername());
            }
        }

        return flow;
    }

    @Override
    public Flow returnToInitialDepartmentFor(Integer flowId, String remark) {
        Flow flow = flowRepo.getOneSQL(flowId);
        flow.setCrtDepartment(0);
        flow.setRemarks(remark);

        flowMailer.setVelocityTemplateLocation(VELOCITY_FLOW_REJECTED_TEMPLATE_LOC);
        flowMailer.sendMailToInitiator(flow);

        log.info(TAG, "Returning flow ID " + flowId + "to the starting point and notifying the flow initiator", flow.getUser().getUsername());

        return flowRepo.save(flow);
    }

    @Override
    public Boolean isFlowAtEnd(Integer flowId) {
        Flow flow = flowRepo.findOne(flowId);
        if (flow == null)
            throw new ServiceException("No flow having ID " + flowId.toString() + " was found in the database");

        log.info(TAG, "Checking if flow ID " + flowId + " is at the end");

        return flow.getCrtDepartment() >= flow.getFlowPath().size();
    }

    @Override
    public Department getCurrentDepartmentFor(Integer flowId) {
        Flow flow = flowRepo.findOne(flowId);
        if (flow == null)
            throw new ServiceException("No flow having ID " + flowId.toString() + " was found in the database");

        int crt = flow.getCrtDepartment();
        if (flow.getFlowPath().size() == 0)
            return null;

        Department tmp = flow.getFlowPath().get(crt).getDepartment();
        tmp = departmentRepository.getOneSQL(tmp.getId());

        log.info(TAG, "Retrieved current department for flow ID " + flowId, "", tmp.getName());

        return tmp;
    }

    @Override
    public List<User> getUsersFromCurrentDepartmentFor(Integer flowId) {
        Department dept = getCurrentDepartmentFor(flowId);
        List<User> users = new ArrayList<>();
        if (dept.getIsUserGroup()) {
            users = dept.getUserList().stream()
                .map(DepartmentUser::getUser)
                .collect(Collectors.toList());
        }
        else {
            Optional<User> chief = dept.getUserList().stream()
                    .filter(departmentUser -> departmentUser.getUser().getUserInfo().getIsDepartmentChief())
                    .map(DepartmentUser::getUser)
                    .findFirst();
            if (!chief.isPresent()) throw new ServiceException("Could not get users for current department because the department has no chief");
            users.add(chief.get());
        }

        log.info(TAG, "Retrieved user list from current department of flow ID " + flowId, "", dept.getName());

        return users;
    }

    @Override
    public List<Flow> getFlowsForDepartment(Integer departmentId) {
        List<Flow> flows = readActive();
        flows = flows.stream()
                .filter(flow -> flow.getFlowPath().get(flow.getCrtDepartment()).getDepartment().getId().equals(departmentId))
                .collect(Collectors.toList());

        log.info(TAG, "Retrieving flows for department", "", flows.isEmpty() ? "" : flows.get(0).getFlowPath().get(0).getDepartment().getName());

        return flows;
    }

    @Override
    public List<Flow> getFlowsForUser(String username) {
        log.info(TAG, "Retrieving flows for user", username);
        DepartmentUser deptUser = departmentUserRepository.getDepartmentUserForUserSQL(username);
         if (!deptUser.getUser().getUserInfo().getIsDepartmentChief() &&
                !deptUser.getDepartment().getIsUserGroup()) // only department chiefs or people from user groups have flows to approve
            return new ArrayList<>();

        return getFlowsForDepartment(deptUser.getDepartment().getId());
    }

    @Override
    public Flow update(Flow updatedFlow) {
        Flow flow = flowRepo.findOne(updatedFlow.getId());
        if (flow == null)
            throw new ServiceException("No flow having ID " + updatedFlow.getId().toString() + " was found in the database");

        List<FlowDocument> flowDocuments = updatedFlow.getFlowDocuments();
        List<FlowPath> flowPath = updatedFlow.getFlowPath();

        List<FlowDocument> dbFlowDocs = new ArrayList<>();
        for (FlowDocument flowDocument : flowDocuments) {
            FlowDocument tmp = flowDocumentRepo.findOne(new FlowDocumentPK(flowDocument.getFlow(), flowDocument.getDocument()));
            dbFlowDocs.add(tmp);
        }

        List<FlowPath> dbFlowPath = new ArrayList<>();
        for (FlowPath flowDepartment : flowPath) {
            FlowPath tmp = flowPathRepo.getOneSQL(flowDepartment.getFlow().getId(), flowDepartment.getDepartment().getId());
            tmp.setDepartment(departmentRepository.getOneSQL(flowDepartment.getDepartment().getId()));
            dbFlowPath.add(tmp);
        }

        flow.setRemarks(updatedFlow.getRemarks());
        flow.setCrtDepartment(updatedFlow.getCrtDepartment());
        flow.setFlowPath(dbFlowPath);
        flow.setFlowDocuments(dbFlowDocs);

        flow = flowRepo.save(flow);

        log.info(TAG, "Updated flow ID " + flow.getId());

        return flow;
    }

    @Override
    public void addRemarks(Integer flowId, String remarks) {
        Flow flow = flowRepo.getOneSQL(flowId);
        flow.setRemarks(remarks);
        flowRepo.save(flow);
        Department crt = null;
        if (flow.getCrtDepartment() < flow.getFlowPath().size())
            crt = flow.getFlowPath().get(flow.getCrtDepartment()).getDepartment();
        String crtDepartment = crt != null ? crt.getName() : "";
        log.info(TAG, "Added remarks to flow ID " + flowId, "", crtDepartment);
    }

    @Override
    public void rejectFlow(Integer flowId) {
        Flow flow = flowRepo.findOne(flowId);
        List<FlowPath> flowPath = flowPathRepo.getSomeSQL(flowId);
        Department crt = null;
        if (flow.getCrtDepartment() < flow.getFlowPath().size())
            crt = flow.getFlowPath().get(flow.getCrtDepartment()).getDepartment();
        flow.setCrtDepartment(flowPath.size());
        String crtDepartment = crt != null ? crt.getName() : "";
        log.info(TAG, "Rejected flow ID " + flowId, "", crtDepartment);
    }

    @Override
    public void delete(Integer flowId) {
        flowRepo.delete(flowId);
    }
}
