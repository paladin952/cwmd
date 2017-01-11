package application.core.repository;

import application.core.utils.enums.LogLevel;
import application.core.model.logging.LogItem;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;

import java.sql.Timestamp;
import java.util.List;

// TODO: create one get function to rule them all, to avoid code duplication
public class LogItemRepositoryImpl extends CustomRepositorySupport<Long, LogItem> implements LogItemRepositoryCustom {
    public LogItemRepositoryImpl() {
        super(LogItem.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LogItem> getAll(String filter) {
        HibernateEntityManager manager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = manager.getSession();
        String sql = "SELECT DISTINCT * " +
                "FROM cwmd_db.log L " +
                "WHERE L.Tag LIKE :filter OR L.User LIKE :filter OR L.Department LIKE :filter OR L.DocumentType LIKE :filter";
        Query select = session.createSQLQuery(sql)
                .addEntity("L", LogItem.class)
                .setString("filter", filter)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List logs = select.list();

        return (List<LogItem>) logs;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LogItem> getAll(Timestamp from, Timestamp to) {
        HibernateEntityManager manager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = manager.getSession();
        String sql = "SELECT DISTINCT * " +
                "FROM cwmd_db.log L " +
                "WHERE L.Timestamp BETWEEN DATE(:date_from) AND DATE(:date_to)";
        Query select = session.createSQLQuery(sql)
                .addEntity("L", LogItem.class)
                .setString("date_from", from.toString())
                .setString("date_to", to.toString())
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List logs = select.list();

        return (List<LogItem>) logs;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LogItem> getAll(Timestamp from, Timestamp to, String filter) {
        HibernateEntityManager manager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = manager.getSession();
        String sql = "SELECT DISTINCT * " +
                "FROM cwmd_db.log L " +
                "WHERE (L.Timestamp BETWEEN DATE(:date_from) AND DATE(:date_to)) AND (L.Tag LIKE :filter OR L.User LIKE :filter OR L.Department LIKE :filter OR L.DocumentType LIKE :filter)";
        Query select = session.createSQLQuery(sql)
                .addEntity("L", LogItem.class)
                .setString("date_from", from.toString())
                .setString("date_to", to.toString())
                .setString("filter", filter)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List logs = select.list();

        return (List<LogItem>) logs;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LogItem> getFor(LogLevel level) {
        HibernateEntityManager manager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = manager.getSession();
        String sql = "SELECT DISTINCT * " +
                "FROM cwmd_db.log L " +
                "WHERE L.Level = :loglevel";
        Query select = session.createSQLQuery(sql)
                .addEntity("L", LogItem.class)
                .setInteger("loglevel", LogLevel.toValue(level))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List logs = select.list();

        return (List<LogItem>) logs;
    }

    @Override
    public List<LogItem> getDebug() {
        return getFor(LogLevel.LOG_DEBUG);
    }

    @Override
    public List<LogItem> getInfo() {
        return getFor(LogLevel.LOG_INFO);
    }

    @Override
    public List<LogItem> getWarnings() {
        return getFor(LogLevel.LOG_WARN);
    }

    @Override
    public List<LogItem> getErrors() {
        return getFor(LogLevel.LOG_ERROR);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LogItem> getFor(LogLevel level, Timestamp from, Timestamp to) {
        HibernateEntityManager manager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = manager.getSession();
        String sql = "SELECT DISTINCT * " +
                "FROM cwmd_db.log L " +
                "WHERE L.Level = :loglevel AND (L.Timestamp BETWEEN DATE(:date_from) AND DATE(:date_to))";
        Query select = session.createSQLQuery(sql)
                .addEntity("L", LogItem.class)
                .setInteger("loglevel", LogLevel.toValue(level))
                .setString("date_from", from.toString())
                .setString("date_to", to.toString())
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List logs = select.list();

        return (List<LogItem>) logs;
    }

    @Override
    public List<LogItem> getDebug(Timestamp from, Timestamp to) {
        return getFor(LogLevel.LOG_DEBUG, from, to);
    }

    @Override
    public List<LogItem> getInfo(Timestamp from, Timestamp to) {
        return getFor(LogLevel.LOG_INFO, from, to);
    }

    @Override
    public List<LogItem> getWarnings(Timestamp from, Timestamp to) {
        return getFor(LogLevel.LOG_WARN, from, to);
    }

    @Override
    public List<LogItem> getErrors(Timestamp from, Timestamp to) {
        return getFor(LogLevel.LOG_ERROR, from, to);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LogItem> getFor(LogLevel level, String filter) {
        HibernateEntityManager manager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = manager.getSession();
        String sql = "SELECT DISTINCT * " +
                "FROM cwmd_db.log L " +
                "WHERE L.Level = :loglevel AND (L.Tag LIKE :filter OR L.User LIKE :filter OR L.Department LIKE :filter OR L.DocumentType LIKE :filter)";
        Query select = session.createSQLQuery(sql)
                .addEntity("L", LogItem.class)
                .setInteger("loglevel", LogLevel.toValue(level))
                .setString("filter", filter)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List logs = select.list();

        return (List<LogItem>) logs;
    }

    @Override
    public List<LogItem> getDebug(String filter) {
        return getFor(LogLevel.LOG_DEBUG, filter);
    }

    @Override
    public List<LogItem> getInfo(String filter) {
        return getFor(LogLevel.LOG_INFO, filter);
    }

    @Override
    public List<LogItem> getWarnings(String filter) {
        return getFor(LogLevel.LOG_WARN, filter);
    }

    @Override
    public List<LogItem> getErrors(String filter) {
        return getFor(LogLevel.LOG_ERROR, filter);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LogItem> getFor(LogLevel level, Timestamp from, Timestamp to, String filter) {
        HibernateEntityManager manager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = manager.getSession();
        String sql = "SELECT DISTINCT * " +
                "FROM cwmd_db.log L " +
                "WHERE L.Level = :loglevel AND (L.Timestamp BETWEEN DATE(:date_from) AND DATE(:date_to)) AND (L.Tag LIKE :filter OR L.User LIKE :filter OR L.Department LIKE :filter OR L.DocumentType LIKE :filter)";
        Query select = session.createSQLQuery(sql)
                .addEntity("L", LogItem.class)
                .setInteger("loglevel", LogLevel.toValue(level))
                .setString("date_from", from.toString())
                .setString("date_to", to.toString())
                .setString("filter", filter)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List logs = select.list();

        return (List<LogItem>) logs;
    }

    @Override
    public List<LogItem> getDebug(Timestamp from, Timestamp to, String filter) {
        return getFor(LogLevel.LOG_DEBUG, from, to, filter);
    }

    @Override
    public List<LogItem> getInfo(Timestamp from, Timestamp to, String filter) {
        return getFor(LogLevel.LOG_INFO, from, to, filter);
    }

    @Override
    public List<LogItem> getWarnings(Timestamp from, Timestamp to, String filter) {
        return getFor(LogLevel.LOG_WARN, from, to, filter);
    }

    @Override
    public List<LogItem> getErrors(Timestamp from, Timestamp to, String filter) {
        return getFor(LogLevel.LOG_ERROR, from, to, filter);
    }
}
