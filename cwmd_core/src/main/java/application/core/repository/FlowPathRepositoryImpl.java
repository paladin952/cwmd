package application.core.repository;

import application.core.model.FlowPath;
import application.core.model.PKs.FlowPathPK;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;

import java.util.List;

/**
 * Flow path repo implementation
 */
public class FlowPathRepositoryImpl extends CustomRepositorySupport<FlowPathPK, FlowPath> implements FlowPathRepositoryCustom {

    public FlowPathRepositoryImpl() {
        super(FlowPath.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<FlowPath> getAllSQL() {
        HibernateEntityManager manager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = manager.getSession();
        String sql = "SELECT DISTINCT * " +
                "FROM cwmd_db.flow_path FP " +
                "LEFT JOIN cwmd_db.flow F ON F.FlowID = FP.FlowID " +
                "LEFT JOIN cwmd_db.department D ON D.DepartmentID = FP.DepartmentID";
        Query select = session.createSQLQuery(sql)
                .addEntity("FP", FlowPath.class)
                .addJoin("F", "FP.flow")
                .addJoin("D", "FP.department")
                .addEntity("FP", FlowPath.class) // intended. need to add the entity again after the joins
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List flowPaths = select.list();

        return (List<FlowPath>) flowPaths;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public FlowPath getOneSQL(Integer flowId, Integer departmentId) {
        HibernateEntityManager manager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = manager.getSession();
        String sql = "SELECT DISTINCT * " +
                "FROM cwmd_db.flow_path FP " +
                "LEFT JOIN cwmd_db.flow F ON F.FlowID = FP.FlowID " +
                "LEFT JOIN cwmd_db.department D ON D.DepartmentID = FP.DepartmentID " +
                "WHERE FP.DepartmentID = :dept_id AND FP.FlowID = :flow_id";
        Query select = session.createSQLQuery(sql)
                .addEntity("FP", FlowPath.class)
                .addJoin("F", "FP.flow")
                .addJoin("D", "FP.department")
                .addEntity("FP", FlowPath.class) // intended. need to add the entity again after the joins
                .setInteger("flow_id", flowId)
                .setInteger("dept_id", departmentId)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List flowPath = select.list();

        FlowPath dept = null;

        if (!flowPath.isEmpty())
            dept = (FlowPath) flowPath.get(0);

        return dept;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<FlowPath> getSomeSQL(Integer flowId) {
        HibernateEntityManager manager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = manager.getSession();
        String sql = "SELECT DISTINCT * " +
                "FROM cwmd_db.flow_path FP " +
                "LEFT JOIN cwmd_db.flow F ON F.FlowID = FP.FlowID " +
                "LEFT JOIN cwmd_db.department D ON D.DepartmentID = FP.DepartmentID " +
                "WHERE FP.FlowID = :flow_id";
        Query select = session.createSQLQuery(sql)
                .addEntity("FP", FlowPath.class)
                .addJoin("F", "FP.flow")
                .addJoin("D", "FP.department")
                .addEntity("FP", FlowPath.class) // intended. need to add the entity again after the joins
                .setInteger("flow_id", flowId)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List flowPath = select.list();

        return (List<FlowPath>) flowPath;
    }
}
