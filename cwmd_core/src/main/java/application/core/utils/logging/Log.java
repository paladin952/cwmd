package application.core.utils.logging;

import application.core.model.logging.LogItem;
import application.core.repository.LogItemRepository;
import application.core.utils.enums.LogLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;


@Component
public class Log {
    @Autowired
    private LogItemRepository logRepo;

    private void oneLogToRuleThemAll(LogLevel level, String tag, String message, String user, String department, String documentType, Throwable tr)
    {
        LogItem item = LogItem.builder()
                .level(LogLevel.toValue(level))
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .tag(tag)
                .msg(message)
                .user(user)
                .department(department)
                .documentType(documentType)
                .exception(tr != null ? tr.getMessage() : null)
                .build();
        logRepo.save(item);
        System.out.println(item.toString());
    }

    public void debug(String tag, String message)
    {
        oneLogToRuleThemAll(LogLevel.LOG_DEBUG, tag, message, null, null, null, null);
    }

    public void debug(String tag, String message, Throwable tr)
    {
        oneLogToRuleThemAll(LogLevel.LOG_DEBUG, tag, message, null, null, null, tr);
    }

    public void debug(String tag, String message, String user)
    {
        oneLogToRuleThemAll(LogLevel.LOG_DEBUG, tag, message, user, null, null, null);
    }

    public void debug(String tag, String message, String user, Throwable tr)
    {
        oneLogToRuleThemAll(LogLevel.LOG_DEBUG, tag, message, user, null, null, tr);
    }

    public void debug(String tag, String message, String user, String department)
    {
        oneLogToRuleThemAll(LogLevel.LOG_DEBUG, tag, message, user, department, null, null);
    }

    public void debug(String tag, String message, String user, String department, Throwable tr)
    {
        oneLogToRuleThemAll(LogLevel.LOG_DEBUG, tag, message, user, department, null, tr);
    }

    public void debug(String tag, String message, String user, String department, String documentType)
    {
        oneLogToRuleThemAll(LogLevel.LOG_DEBUG, tag, message, user, department, documentType, null);
    }

    public void debug(String tag, String message, String user, String department, String documentType, Throwable tr)
    {
        oneLogToRuleThemAll(LogLevel.LOG_DEBUG, tag, message, user, department, documentType, tr);
    }


    public void info(String tag, String message)
    {
        oneLogToRuleThemAll(LogLevel.LOG_INFO, tag, message, null, null, null, null);
    }

    public void info(String tag, String message, Throwable tr)
    {
        oneLogToRuleThemAll(LogLevel.LOG_INFO, tag, message, null, null, null, tr);
    }

    public void info(String tag, String message, String user)
    {
        oneLogToRuleThemAll(LogLevel.LOG_INFO, tag, message, user, null, null, null);
    }

    public void info(String tag, String message, String user, Throwable tr)
    {
        oneLogToRuleThemAll(LogLevel.LOG_INFO, tag, message, user, null, null, tr);
    }

    public void info(String tag, String message, String user, String department)
    {
        oneLogToRuleThemAll(LogLevel.LOG_INFO, tag, message, user, department, null, null);
    }

    public void info(String tag, String message, String user, String department, Throwable tr)
    {
        oneLogToRuleThemAll(LogLevel.LOG_INFO, tag, message, user, department, null, tr);
    }

    public void info(String tag, String message, String user, String department, String documentType)
    {
        oneLogToRuleThemAll(LogLevel.LOG_INFO, tag, message, user, department, documentType, null);
    }

    public void info(String tag, String message, String user, String department, String documentType, Throwable tr)
    {
        oneLogToRuleThemAll(LogLevel.LOG_INFO, tag, message, user, department, documentType, tr);
    }


    public void warn(String tag, String message)
    {
        oneLogToRuleThemAll(LogLevel.LOG_WARN, tag, message, null, null, null, null);
    }

    public void warn(String tag, String message, Throwable tr)
    {
        oneLogToRuleThemAll(LogLevel.LOG_WARN, tag, message, null, null, null, tr);
    }

    public void warn(String tag, String message, String user)
    {
        oneLogToRuleThemAll(LogLevel.LOG_WARN, tag, message, user, null, null, null);
    }

    public void warn(String tag, String message, String user, Throwable tr)
    {
        oneLogToRuleThemAll(LogLevel.LOG_WARN, tag, message, user, null, null, tr);
    }

    public void warn(String tag, String message, String user, String department)
    {
        oneLogToRuleThemAll(LogLevel.LOG_WARN, tag, message, user, department, null, null);
    }

    public void warn(String tag, String message, String user, String department, Throwable tr)
    {
        oneLogToRuleThemAll(LogLevel.LOG_WARN, tag, message, user, department, null, tr);
    }

    public void warn(String tag, String message, String user, String department, String documentType)
    {
        oneLogToRuleThemAll(LogLevel.LOG_WARN, tag, message, user, department, documentType, null);
    }

    public void warn(String tag, String message, String user, String department, String documentType, Throwable tr)
    {
        oneLogToRuleThemAll(LogLevel.LOG_WARN, tag, message, user, department, documentType, tr);
    }


    public void error(String tag, String message)
    {
        oneLogToRuleThemAll(LogLevel.LOG_ERROR, tag, message, null, null, null, null);
    }

    public void error(String tag, String message, Throwable tr)
    {
        oneLogToRuleThemAll(LogLevel.LOG_ERROR, tag, message, null, null, null, tr);
    }

    public void error(String tag, String message, String user)
    {
        oneLogToRuleThemAll(LogLevel.LOG_ERROR, tag, message, user, null, null, null);
    }

    public void error(String tag, String message, String user, Throwable tr)
    {
        oneLogToRuleThemAll(LogLevel.LOG_ERROR, tag, message, user, null, null, tr);
    }

    public void error(String tag, String message, String user, String department)
    {
        oneLogToRuleThemAll(LogLevel.LOG_ERROR, tag, message, user, department, null, null);
    }

    public void error(String tag, String message, String user, String department, Throwable tr)
    {
        oneLogToRuleThemAll(LogLevel.LOG_ERROR, tag, message, user, department, null, tr);
    }

    public void error(String tag, String message, String user, String department, String documentType)
    {
        oneLogToRuleThemAll(LogLevel.LOG_ERROR, tag, message, user, department, documentType, null);
    }

    public void error(String tag, String message, String user, String department, String documentType, Throwable tr)
    {
        oneLogToRuleThemAll(LogLevel.LOG_ERROR, tag, message, user, department, documentType, tr);
    }
}
