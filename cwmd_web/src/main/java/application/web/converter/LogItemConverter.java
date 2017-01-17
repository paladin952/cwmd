package application.web.converter;

import application.core.model.logging.LogItem;
import application.core.utils.enums.LogLevel;
import application.web.dto.LogItemDTO;
import org.springframework.stereotype.Component;

/**
 * Converting {@link LogItem} to {@link LogItemDTO} or the other way around
 */
@Component
public class LogItemConverter extends Converter<LogItem, LogItemDTO> {

    /**
     * Converting a {@link LogItem} to a {@link LogItemDTO}
     * @param logItem The LogItem as input
     * @return a new {@link LogItemDTO}
     */
    @Override
    public LogItemDTO toDTO(LogItem logItem) {
        return LogItemDTO.builder()
                .level(LogLevel.toString(LogLevel.fromValue(logItem.getLevel())))
                .timestamp(logItem.getTimestamp().toString())
                .tag(logItem.getTag())
                .msg(logItem.getMsg())
                .user(logItem.getUser())
                .department(logItem.getDepartment())
                .documentType(logItem.getDocumentType())
                .exception(logItem.getException())
                .build();
    }

    /**
     * Converting a {@link LogItemDTO} to a {@link LogItem}
     * @param logItemDTO The LogItemDto as input
     * @return a new {@link LogItem}
     */
    @Override
    @Deprecated
    public LogItem fromDTO(LogItemDTO logItemDTO) {
        return new LogItem();
    }
}
