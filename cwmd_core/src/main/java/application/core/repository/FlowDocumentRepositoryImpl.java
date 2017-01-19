package application.core.repository;

import application.core.model.FlowDocument;
import application.core.model.PKs.FlowDocumentPK;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;

import java.util.List;

public class FlowDocumentRepositoryImpl extends CustomRepositorySupport<FlowDocumentPK, FlowDocument> implements FlowDocumentRepositoryCustom {
    public FlowDocumentRepositoryImpl() {
        super(FlowDocument.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<FlowDocument> getSomeSQL(Integer flowId) {
        HibernateEntityManager manager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = manager.getSession();
        String sql = "SELECT DISTINCT * " +
                "FROM cwmd_db.flow_document FD " +
                "LEFT JOIN cwmd_db.flow F ON F.FlowID = FD.FlowID " +
                "LEFT JOIN cwmd_db.document D ON D.DocumentID = FD.DocumentID " +
                "WHERE FD.FlowID = :flow_id";
        Query select = session.createSQLQuery(sql)
                .addEntity("FD", FlowDocument.class)
                .addJoin("F", "FD.flow")
                .addJoin("D", "FD.document")
                .addEntity("FD", FlowDocument.class) // intended. need to add the entity again after the joins
                .setInteger("flow_id", flowId)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List flowDocuments = select.list();

        return (List<FlowDocument>) flowDocuments;
    }
}
