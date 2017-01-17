package application.core.repository;

import application.core.utils.enums.LogLevel;
import application.core.model.logging.LogItem;

import java.sql.Timestamp;
import java.util.List;

/**
 * Custom repo for {@link LogItem}
 */
public interface LogItemRepositoryCustom {

    /**
     * Get all logs
     * @param filter Filter by
     */
    List<LogItem> getAll(String filter);

    /**
     * Get all logs filtered by
     * @param from time from
     * @param to time until
     */
    List<LogItem> getAll(Timestamp from, Timestamp to);

    /**
     * Get all filtered by
     * @param from time from
     * @param to time to
     * @param filter extra filtering
     */
    List<LogItem> getAll(Timestamp from, Timestamp to, String filter);

    /**
     * Get logs for a:
     * @param level specific level
     */
    List<LogItem> getFor(LogLevel level);

    /**
     * Get debug level
     */
    List<LogItem> getDebug();

    /**
     * Get info level
     */
    List<LogItem> getInfo();

    /**
     * Get warning s level
     */
    List<LogItem> getWarnings();

    /**
     * Get errors level
     */
    List<LogItem> getErrors();

    /**
     * Get filtered for
     * @param level The level
     * @param from time from
     * @param to time until
     */
    List<LogItem> getFor(LogLevel level, Timestamp from, Timestamp to);

    /**
     * Get filtered for debug
     * @param from time from
     * @param to time until
     */
    List<LogItem> getDebug(Timestamp from, Timestamp to);

    /**
     * Get filtered for info
     * @param from time from
     * @param to time until
     */
    List<LogItem> getInfo(Timestamp from, Timestamp to);

    /**
     * Get all warnings
     * @param from time from
     * @param to time until
     */
    List<LogItem> getWarnings(Timestamp from, Timestamp to);

    /**
     * Get all errors
     * @param from time from
     * @param to time until
     */
    List<LogItem> getErrors(Timestamp from, Timestamp to);


    /**
     * Get for
     * @param level the level
     * @param filter the filter
     */
    List<LogItem> getFor(LogLevel level, String filter);

    /**
     * Get for debug
     * @param filter the filter
     */
    List<LogItem> getDebug(String filter);

    /**
     * Get for info
     * @param filter the filter
     */
    List<LogItem> getInfo(String filter);

    /**
     * Get for warnings
     * @param filter the filter
     */
    List<LogItem> getWarnings(String filter);

    /**
     * Get for errors
     * @param filter the filter
     */
    List<LogItem> getErrors(String filter);


    /**
     * Get for
     * @param level the level
     * @param from time from
     * @param to time to
     * @param filter extra filter
     */
    List<LogItem> getFor(LogLevel level, Timestamp from, Timestamp to, String filter);

    /**
     * Get for debug
     * @param from time from
     * @param to time to
     * @param filter extra filter
     */
    List<LogItem> getDebug(Timestamp from, Timestamp to, String filter);

    /**
     * Get for info
     * @param from time from
     * @param to time to
     * @param filter extra filter
     */
    List<LogItem> getInfo(Timestamp from, Timestamp to, String filter);

    /**
     * Get for warnings
     * @param from time from
     * @param to time to
     * @param filter extra filter
     */
    List<LogItem> getWarnings(Timestamp from, Timestamp to, String filter);

    /**
     * Get for errors
     * @param from time from
     * @param to time to
     * @param filter extra filter
     */
    List<LogItem> getErrors(Timestamp from, Timestamp to, String filter);
}
