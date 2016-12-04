package application.core.service;

import application.core.model.*;
import application.core.repository.FlowDocumentRepository;
import application.core.repository.FlowPathRepository;
import application.core.repository.FlowRepository;
import application.core.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FlowServiceImpl implements IFlowService {
    @Autowired
    private FlowRepository flowRepo;
    @Autowired
    private FlowPathRepository flowPathRepo;
    @Autowired
    private FlowDocumentRepository flowDocumentRepo;

    @Override
    public Flow startFlow(List<Document> documents, List<Department> departments) {
        try {
            Flow flow = Flow.builder()
                    .crtDepartment(0)
                    .remarks("")
                    .build();

            flowRepo.save(flow);

            for (Document doc : documents)
            {
                FlowDocument tmp = FlowDocument.builder()
                        .flow(flow)
                        .document(doc)
                        .build();
                flowDocumentRepo.save(tmp);
            }

            for (Department department : departments)
            {
                FlowPath tmp = FlowPath.builder()
                        .flow(flow)
                        .department(department)
                        .build();
                flowPathRepo.save(tmp);
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
    public Flow readOne(Integer flowId) {
        return flowRepo.findOne(flowId);
    }

    @Override
    public Flow goToNextDepartmentFor(Integer flowId) {
        Flow flow = flowRepo.findOne(flowId);
        if (flow == null) throw new ServiceException("No flow having ID " + flowId.toString() + " was found in the database");

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
        if (flow == null) throw new ServiceException("No flow having ID " + flowId.toString() + " was found in the database");

        return flow.getCrtDepartment() >= flow.getFlowPath().size();
    }

    @Override
    public Department getCurrentDepartmentFor(Integer flowId) {
        Flow flow = flowRepo.findOne(flowId);
        if (flow == null) throw new ServiceException("No flow having ID " + flowId.toString() + " was found in the database");

        int crt = flow.getCrtDepartment();
        return flow.getFlowPath().get(crt).getDepartment();
    }

    @Override
    public Flow update(Flow updatedFlow) {
        Flow flow = flowRepo.findOne(updatedFlow.getId());
        if (flow == null) throw new ServiceException("No flow having ID " + updatedFlow.getId().toString() + " was found in the database");

        List<FlowDocument> flowDocuments = updatedFlow.getFlowDocuments();
        List<FlowPath> flowPath = updatedFlow.getFlowPath();
        flowDocuments.forEach(flowDocument -> flowDocumentRepo.save(flowDocument));
        flowPath.forEach(flowDepartments -> flowPathRepo.save(flowDepartments));

        flow.setRemarks(updatedFlow.getRemarks());
        flow.setCrtDepartment(updatedFlow.getCrtDepartment());
        flow.setFlowPath(flowPath);
        flow.setFlowDocuments(flowDocuments);

        return flowRepo.save(flow);
    }

    @Override
    public IFlowService delete(Integer flowId) {
        flowRepo.delete(flowId);

        return this;
    }
}
