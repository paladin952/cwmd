package application.core.repository;

import application.core.model.Department;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;

import java.util.List;

public class DepartmentRepositoryImpl extends CustomRepositorySupport<Integer, Department> implements DepartmentRepositoryCustom {
    public DepartmentRepositoryImpl() {
        super(Department.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Department> getAllSQL() {
        HibernateEntityManager manager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = manager.getSession();
        String sql = "SELECT DISTINCT * " +
                "FROM cwmd_db.department DEPT " +
                "LEFT JOIN cwmd_db.department_user DU ON DU.DepartmentID = DEPT.DepartmentID " +
                "LEFT JOIN cwmd_db.user U ON U.Username = DU.Username";
        Query select = session.createSQLQuery(sql)
                .addEntity("DEPT", Department.class)
                .addJoin("DU", "DEPT.userList")
                .addJoin("U", "DU.user")
                .addEntity("DEPT", Department.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List departments = select.list();

        return (List<Department>) departments;
    }

    @Override
    public Department getOneSQL(Integer id) {
        HibernateEntityManager manager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = manager.getSession();
        String sql = "SELECT DISTINCT * " +
                "FROM cwmd_db.department DEPT " +
                "LEFT JOIN cwmd_db.department_user DU ON DU.DepartmentID = DEPT.DepartmentID " +
                "LEFT JOIN cwmd_db.user U ON U.Username = DU.Username " +
                "WHERE DEPT.DepartmentID = :dept_id";
        Query select = session.createSQLQuery(sql)
                .addEntity("DEPT", Department.class)
                .addJoin("DU", "DEPT.userList")
                .addJoin("U", "DU.user")
                .addEntity("DEPT", Department.class) // intended. need to add the entity again after the joins, otherwise we get a list of users instead of departments
                .setInteger("dept_id", id)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List depts = select.list();

        Department dept = null;

        if (!depts.isEmpty())
            dept = (Department) depts.get(0);

        return dept;
    }
}
