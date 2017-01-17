package application.core.service;

import application.core.model.logging.LogItem;
import application.core.repository.LogItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

/**
 * Service for {@link LogItem}
 */
@Service
@Transactional
public class LogService {
    @Autowired
    private LogItemRepository logRepo;

    /**
     * Returns all log items.
     *
     * @return A list of {@link LogItem}
     */
    public List<LogItem> getAll() {
        return logRepo.findAll();
    }

    /**
     * Returns all log items with a specified filter.
     *
     * @param filter the filter for log items.
     * @return A list of filtered {@link LogItem}.
     */
    public List<LogItem> getAll(String filter) {
        return logRepo.getAll(filter);
    }

    /**
     * Returns all log items between two timestamps.
     *
     * @param from Starting Timestamp.
     * @param to   Ending TimeStamp.
     * @return A list of {@link LogItem}.
     */
    public List<LogItem> getAll(Timestamp from, Timestamp to) {
        return logRepo.getAll(from, to);
    }

    /**
     * Returns all log items between two timestamps and a specified filter
     *
     * @param from   Starting Timestamp.
     * @param to     Ending TimeStamp.
     * @param filter The filter
     * @return A list of {@link LogItem}.
     */
    public List<LogItem> getAll(Timestamp from, Timestamp to, String filter) {
        return logRepo.getAll(from, to, filter);
    }

    /**
     * Returns all debug log items.
     *
     * @return A list of {@link LogItem}
     */
    public List<LogItem> getDebug() {
        return logRepo.getDebug();
    }

    /**
     * Returns all info log items.
     *
     * @return A list of {@link LogItem}
     */
    public List<LogItem> getInfo() {
        return logRepo.getInfo();
    }

    /**
     * Returns all warning log items.
     *
     * @return A list of {@link LogItem}
     */
    public List<LogItem> getWarnings() {
        return logRepo.getWarnings();
    }

    /**
     * Returns all error log items.
     *
     * @return A list of {@link LogItem}
     */
    public List<LogItem> getErrors() {
        return logRepo.getErrors();
    }

    /**
     * Returns all debug log items between two timestamps
     *
     * @param from Starting Timestamp.
     * @param to   Ending Timestamp.
     * @return A list of {@link LogItem}
     */
    public List<LogItem> getDebug(Timestamp from, Timestamp to) {
        return logRepo.getDebug(from, to);
    }

    /**
     * Returns all info log items between two timestamps
     *
     * @param from Starting Timestamp.
     * @param to   Ending Timestamp.
     * @return A list of {@link LogItem}
     */
    public List<LogItem> getInfo(Timestamp from, Timestamp to) {
        return logRepo.getInfo(from, to);
    }

    /**
     * Returns all warning log items between two timestamps
     *
     * @param from Starting Timestamp.
     * @param to   Ending Timestamp.
     * @return A list of {@link LogItem}
     */
    public List<LogItem> getWarnings(Timestamp from, Timestamp to) {
        return logRepo.getWarnings(from, to);
    }

    /**
     * Returns all error log items between two timestamps
     *
     * @param from Starting Timestamp.
     * @param to   Ending Timestamp.
     * @return A list of {@link LogItem}
     */
    public List<LogItem> getErrors(Timestamp from, Timestamp to) {
        return logRepo.getErrors(from, to);
    }

    /**
     * Returns all debug log items with a given filter
     *
     * @param filter The log item filter
     * @return A list of {@link LogItem}
     */
    public List<LogItem> getDebug(String filter) {
        return logRepo.getDebug(filter);
    }

    /**
     * Returns all info log items with a given filter
     *
     * @param filter The log item filter
     * @return A list of {@link LogItem}
     */
    public List<LogItem> getInfo(String filter) {
        return logRepo.getInfo(filter);
    }

    /**
     * Returns all warning log items with a given filter
     *
     * @param filter The log item filter
     * @return A list of {@link LogItem}
     */
    public List<LogItem> getWarnings(String filter) {
        return logRepo.getWarnings(filter);
    }

    /**
     * Returns all error log items with a given filter
     *
     * @param filter The log item filter
     * @return A list of {@link LogItem}
     */
    public List<LogItem> getErrors(String filter) {
        return logRepo.getErrors(filter);
    }

    /**
     * Returns all debug log items between two timestamps with a given filter
     *
     * @param from   Starting Timestamp.
     * @param to     Ending Timestamp.
     * @param filter The log item filter
     * @return A list of {@link LogItem}
     */
    public List<LogItem> getDebug(Timestamp from, Timestamp to, String filter) {
        return logRepo.getDebug(from, to, filter);
    }

    /**
     * Returns all info log items between two timestamps with a given filter
     *
     * @param from   Starting Timestamp.
     * @param to     Ending Timestamp.
     * @param filter The log item filter
     * @return A list of {@link LogItem}
     */
    public List<LogItem> getInfo(Timestamp from, Timestamp to, String filter) {
        return logRepo.getInfo(from, to, filter);
    }

    /**
     * Returns all warning log items between two timestamps with a given filter
     *
     * @param from   Starting Timestamp.
     * @param to     Ending Timestamp.
     * @param filter The log item filter
     * @return A list of {@link LogItem}
     */
    public List<LogItem> getWarnings(Timestamp from, Timestamp to, String filter) {
        return logRepo.getWarnings(from, to, filter);
    }

    /**
     * Returns all error log items between two timestamps with a given filter
     *
     * @param from   Starting Timestamp.
     * @param to     Ending Timestamp.
     * @param filter The log item filter
     * @return A list of {@link LogItem}
     */
    public List<LogItem> getErrors(Timestamp from, Timestamp to, String filter) {
        return logRepo.getErrors(from, to, filter);
    }
}
