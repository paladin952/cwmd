package application.core.service;

import application.core.model.*;
import application.core.model.PKs.FlowDocumentPK;
import application.core.repository.*;
import application.core.service.exceptions.ServiceException;
import application.core.utils.FlowMailer;
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
    @Autowired
    private FlowRepository flowRepo;
    @Autowired
    private FlowPathRepository flowPathRepo;
    @Autowired
    private FlowDocumentRepository flowDocumentRepo;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlowMailer flowMailer;

    @Override
    public Flow startFlow(List<Integer> documents, List<Integer> departments, String username) {
        User user = userRepository.findOne(username);

        try {
            Flow flow = Flow.builder()
                    .crtDepartment(0)
                    .user(user)
                    .remarks("")
                    .flowPath(new ArrayList<>())
                    .flowDocuments(new ArrayList<>())
                    .build();

            flow = flowRepo.saveAndFlush(flow); // TODO: shouldn't this be done at the end?
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

            return flow;
        } catch (Exception e) {
            throw new ServiceException("Unknown error while starting a flow", e);
        }
    }

    @Override
    public List<Flow> read(String username) {
        return flowRepo.findByUser_Username(username);
    }

    @Override
    public List<Flow> readAll() {
        return flowRepo.findAll();
    }

    @Override
    public List<Flow> readActive(String username) {
        return flowRepo.findByUser_Username(username)
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

    @Override
    public Flow readOne(Integer flowId) {
        Flow flow = flowRepo.getOneSQL(flowId);
        if (flow == null)
            return null;
//
//        flow.setFlowPath(flowPathRepo.getSomeSQL(flow.getId()));
//        for (FlowPath path : flow.getFlowPath()) {
//            path.setDepartment(departmentRepository.getOneSQL(path.getDepartment().getId()));
//        }

        return flow;
    }

    @Override
    public Flow goToNextDepartmentFor(Integer flowId) {
        Flow flow = flowRepo.findOne(flowId);
        if (flow == null)
            throw new ServiceException("No flow having ID " + flowId.toString() + " was found in the database");

        int crt = flow.getCrtDepartment();
        flow.setCrtDepartment(crt == flow.getFlowPath().size() - 1 ? crt : crt + 1); // stop going further once we're at the end of the road
        flowRepo.save(flow);

        flowMailer.SendMail(flow);

        return flow;
    }

    @Override
    public Flow returnToInitialDepartmentFor(Integer flowId, String remark) {
        Flow flow = flowRepo.findOne(flowId);
        flow.setCrtDepartment(0);
        flow.setRemarks(remark);

        return flowRepo.save(flow);
    }

    @Override
    public Boolean isFlowAtEnd(Integer flowId) {
        Flow flow = flowRepo.findOne(flowId);
        if (flow == null)
            throw new ServiceException("No flow having ID " + flowId.toString() + " was found in the database");

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
        return departmentRepository.getOneSQL(tmp.getId());
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

        return users;
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

        return flowRepo.save(flow);
    }

    @Override
    public void addRemarks(Integer flowId, String remarks) {
        Flow flow = flowRepo.findOne(flowId);
        flow.setRemarks(remarks);
        flowRepo.save(flow);
    }

    @Override
    public void delete(Integer flowId) {
        flowRepo.delete(flowId);
    }
}
