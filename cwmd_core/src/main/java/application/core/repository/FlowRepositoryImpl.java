package application.core.repository;

import application.core.model.Flow;
import application.core.model.FlowPath;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FlowRepositoryImpl extends CustomRepositorySupport<Integer, Flow> implements FlowRepositoryCustom {
    @Autowired
    private DepartmentRepository departmentRepository;

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
                .addEntity("F", Flow.class) // intended. need to add the entity again after the joins, otherwise we get a list of users instead of departments
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
}
