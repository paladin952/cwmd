package application.core.repository;

import application.core.model.Department;
import application.core.model.DepartmentUser;
import application.core.model.PKs.DepartmentUserPK;
import application.core.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;

import java.util.List;

public class DepartmentUserRepositoryImpl extends CustomRepositorySupport<DepartmentUserPK, DepartmentUser> implements DepartmentUserRepositoryCustom {
    public DepartmentUserRepositoryImpl() {
        super(DepartmentUser.class);
    }

    @Override
    public void saveSQL(DepartmentUser deptUser) {
        saveSQL(deptUser.getDepartment(), deptUser.getUser());
    }

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

    @Override
    public DepartmentUser getDepartmentUserForUserSQL(String username) {
        HibernateEntityManager manager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = manager.getSession();
        String sql = "SELECT * FROM cwmd_db.department_user DU " +
                "LEFT JOIN cwmd_db.department D ON D.DepartmentID = DU.DepartmentID " +
                "LEFT JOIN cwmd_db.user U ON U.Username = DU.Username " +
                "LEFT JOIN cwmd_db.user_details UD ON U.userInfo_EntryID = UD.EntryID " +
                "WHERE DU.Username = :username";
        Query select = session.createSQLQuery(sql)
                .addEntity("DU", DepartmentUser.class)
                .addJoin("D", "DU.department")
                .addJoin("U", "DU.user")
                .addJoin("UD", "U.userInfo")
                .addEntity("DU", DepartmentUser.class)
                .setString("username", username)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List departmentUsers = select.list();

        return !departmentUsers.isEmpty() ? (DepartmentUser) departmentUsers.get(0) : null;
    }
}
