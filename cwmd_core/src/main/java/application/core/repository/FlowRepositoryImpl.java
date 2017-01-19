package application.core.repository;

import application.core.model.*;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlowRepositoryImpl extends CustomRepositorySupport<Integer, Flow> implements FlowRepositoryCustom {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private FlowPathRepository flowPathRepository;

    @Autowired
    private FlowDocumentRepository flowDocumentRepository;

    public FlowRepositoryImpl() {
        super(Flow.class);
    }

    @Override
    public Flow getOneSQL(Integer id) {
        HibernateEntityManager manager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = manager.getSession();
        String sql = "SELECT DISTINCT * " +
                "FROM cwmd_db.flow F " +
                "LEFT JOIN cwmd_db.flow_path FP ON FP.FlowID = F.FlowID " +
                "LEFT JOIN cwmd_db.flow_document FD ON FD.FlowID = F.FlowID " +
                "WHERE F.FlowID = :flow_id";
        Query select = session.createSQLQuery(sql)
                .addEntity("F", Flow.class)
                .addJoin("FP", "F.flowPath")
                .addJoin("FD", "F.flowDocuments")
                .addEntity("F", Flow.class)
                .setInteger("flow_id", id)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List flows = select.list();

        Flow flow = null;

        if (!flows.isEmpty()) {
            flow = (Flow) flows.get(0);
            for (FlowPath path : flow.getFlowPath())
                path.setDepartment(departmentRepository.getOneSQL(path.getDepartment().getId()));
        }

        return flow;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Flow> getAllSQL() {
        HibernateEntityManager manager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = manager.getSession();
        String sql = "SELECT DISTINCT * " +
                "FROM cwmd_db.flow F " +
                "LEFT JOIN cwmd_db.flow_path FP ON FP.FlowID = F.FlowID " +
                "LEFT JOIN cwmd_db.flow_document FD ON FD.FlowID = F.FlowID";
        Query select = session.createSQLQuery(sql)
                .addEntity("F", Flow.class)
                .addJoin("FP", "F.flowPath")
                .addJoin("FD", "F.flowDocuments")
                .addEntity("F", Flow.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Flow> flows = (List<Flow>) select.list();
        for (Flow flow : flows) {
            flow.setFlowPath(flowPathRepository.getSomeSQL(flow.getId()));
            flow.setFlowDocuments(flowDocumentRepository.getSomeSQL(flow.getId()));
        }

        return flows;
    }
}
