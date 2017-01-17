package application.core.service;

import application.core.model.*;
import application.core.model.PKs.FlowDocumentPK;
import application.core.repository.*;
import application.core.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public Flow startFlow(List<Integer> documents, List<Integer> departments) {
        try {
            Flow flow = Flow.builder()
                    .crtDepartment(0)
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

            return flow;
        } catch (Exception e) {
            throw new ServiceException("Unknown error while starting a flow", e);
        }
    }

    @Override
    public List<Flow> read() {
        return flowRepo.findAll();
    }

    @Override
    public List<Flow> readActive() {
        return flowRepo.findAll()
                .stream()
                .filter(flow -> flow.getCrtDepartment() < flow.getFlowPath().size())
                .collect(Collectors.toList());
    }

    @Override
    public List<Flow> readFinished() {
        return flowRepo.findAll()
                .stream()
                .filter(flow -> flow.getCrtDepartment() >= flow.getFlowPath().size())
                .collect(Collectors.toList());
    }

    @Override
    public Flow readOne(Integer flowId) {
        Flow flow = flowRepo.findOne(flowId);
        if (flow == null)
            return null;

        flow.setFlowPath(flowPathRepo.getSomeSQL(flow.getId()));
        for (FlowPath path : flow.getFlowPath()) {
            path.setDepartment(departmentRepository.getOneSQL(path.getDepartment().getId()));
        }

        return flow;
    }

    @Override
    public Flow goToNextDepartmentFor(Integer flowId) {
        Flow flow = flowRepo.findOne(flowId);
        if (flow == null)
            throw new ServiceException("No flow having ID " + flowId.toString() + " was found in the database");

        int crt = flow.getCrtDepartment();
        flow.setCrtDepartment(crt == flow.getFlowPath().size() - 1 ? crt : crt + 1); // stop going further once we're at the end of the road

        return flowRepo.save(flow);
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
    public void delete(Integer flowId) {
        flowRepo.delete(flowId);
    }
}
