package application.core.utils.enums;

/**
 * Enum for log level
 */
public enum LogLevel {
    LOG_DEBUG,
    LOG_INFO,
    LOG_WARN,
    LOG_ERROR,
    LOG_ALL;

    public static String toString(LogLevel level) {
        switch (level) {
            case LOG_DEBUG:
                return "DEBUG";
            case LOG_INFO:
                return "INFO";
            case LOG_WARN:
                return "WARNING";
            case LOG_ERROR:
                return "ERROR";
            default:
                return "DEFAULT";
        }
    }

    public static Integer toValue(LogLevel level) {
        switch (level) {
            case LOG_DEBUG:
                return 0;
            case LOG_INFO:
                return 1;
            case LOG_WARN:
                return 2;
            case LOG_ERROR:
                return 3;
            default:
                return 4;
        }
    }

    public static LogLevel fromValue(Integer value) {
        switch (value) {
            case 0:
                return LOG_DEBUG;
            case 1:
                return LOG_INFO;
            case 2:
                return LOG_WARN;
            case 3:
                return LOG_ERROR;
            default:
                return null;
        }
    }
}
