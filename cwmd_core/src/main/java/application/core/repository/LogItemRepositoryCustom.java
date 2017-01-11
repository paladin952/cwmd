package application.core.repository;

import application.core.utils.enums.LogLevel;
import application.core.model.logging.LogItem;

import java.sql.Timestamp;
import java.util.List;

public interface LogItemRepositoryCustom {
    List<LogItem> getAll(String filter);
    List<LogItem> getAll(Timestamp from, Timestamp to);
    List<LogItem> getAll(Timestamp from, Timestamp to, String filter);

    List<LogItem> getFor(LogLevel level);
    List<LogItem> getDebug();
    List<LogItem> getInfo();
    List<LogItem> getWarnings();
    List<LogItem> getErrors();

    List<LogItem> getFor(LogLevel level, Timestamp from, Timestamp to);
    List<LogItem> getDebug(Timestamp from, Timestamp to);
    List<LogItem> getInfo(Timestamp from, Timestamp to);
    List<LogItem> getWarnings(Timestamp from, Timestamp to);
    List<LogItem> getErrors(Timestamp from, Timestamp to);

    List<LogItem> getFor(LogLevel level, String filter);
    List<LogItem> getDebug(String filter);
    List<LogItem> getInfo(String filter);
    List<LogItem> getWarnings(String filter);
    List<LogItem> getErrors(String filter);

    List<LogItem> getFor(LogLevel level, Timestamp from, Timestamp to, String filter);
    List<LogItem> getDebug(Timestamp from, Timestamp to, String filter);
    List<LogItem> getInfo(Timestamp from, Timestamp to, String filter);
    List<LogItem> getWarnings(Timestamp from, Timestamp to, String filter);
    List<LogItem> getErrors(Timestamp from, Timestamp to, String filter);
}
