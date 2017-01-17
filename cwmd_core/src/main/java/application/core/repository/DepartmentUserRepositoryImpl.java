package application.core.repository;

import application.core.model.Department;
import application.core.model.DepartmentUser;
import application.core.model.PKs.DepartmentUserPK;
import application.core.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;

/**
 * Repo implementation for DepartmentUser
 */
public class DepartmentUserRepositoryImpl extends CustomRepositorySupport<DepartmentUserPK, DepartmentUser> implements DepartmentUserRepositoryCustom {
    public DepartmentUserRepositoryImpl() {
        super(DepartmentUser.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveSQL(DepartmentUser deptUser) {
        saveSQL(deptUser.getDepartment(), deptUser.getUser());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveSQL(Department department, User user) {
        HibernateEntityManager manager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = manager.getSession();
        String sql = "INSERT INTO cwmd_db.department_user (DepartmentID, Username) " +
                "VALUES (" +
                ":dept_id, :user_id)";
        Query insert = session.createSQLQuery(sql)
                .setInteger("dept_id", department.getId())
                .setString("user_id", user.getUsername());
        insert.executeUpdate();
    }
}
