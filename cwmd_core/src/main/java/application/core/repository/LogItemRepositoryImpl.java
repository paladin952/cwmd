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

    @SuppressWarnings("unchecked")
    private List<LogItem> oneGetToRuleThemAll(LogLevel loglevel, Timestamp from, Timestamp to, String filter)
    {
        HibernateEntityManager manager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = manager.getSession();
        String sql = "SELECT DISTINCT * FROM cwmd_db.log L WHERE ";
        switch (loglevel)
        {
            case LOG_DEBUG:
            case LOG_INFO:
            case LOG_WARN:
            case LOG_ERROR:
                sql += "L.level = :loglevel";
            default:
                if (loglevel != LogLevel.LOG_ALL && filter != null)
                    sql += " AND ";
                if (filter != null)
                        sql += "(L.Tag LIKE :filter OR L.User LIKE :filter OR L.Department LIKE :filter OR L.DocumentType LIKE :filter)";
                if ((loglevel != LogLevel.LOG_ALL || filter != null) && (from != null && to != null))
                    sql += " AND ";
                if (from != null && to != null)
                    sql += "L.Timestamp BETWEEN :date_from AND :date_to";
                break;
        }

        Query select = session.createSQLQuery(sql)
                .addEntity("L", LogItem.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (loglevel != LogLevel.LOG_ALL)
            select.setInteger("loglevel", LogLevel.toValue(loglevel));
        if (filter != null)
            select.setString("filter", "%" + filter + "%");
        if (from != null && to != null)
            select.setString("date_from", from.toString())
                .setString("date_to", to.toString());

        List logs = select.list();

        return (List<LogItem>) logs;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LogItem> getAll(String filter) {
        return oneGetToRuleThemAll(LogLevel.LOG_ALL, null, null, filter);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LogItem> getAll(Timestamp from, Timestamp to) {
        return oneGetToRuleThemAll(LogLevel.LOG_ALL, from, to, null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LogItem> getAll(Timestamp from, Timestamp to, String filter) {
        return oneGetToRuleThemAll(LogLevel.LOG_ALL, from, to, filter);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LogItem> getFor(LogLevel level) {
        return oneGetToRuleThemAll(level, null, null, null);
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
        return oneGetToRuleThemAll(level, from, to, null);
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
        return oneGetToRuleThemAll(level, null, null, filter);
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
        return oneGetToRuleThemAll(level, from, to, filter);
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
