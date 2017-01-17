package application.web.converter;

import application.core.model.logging.LogItem;
import application.core.utils.enums.LogLevel;
import application.web.dto.LogItemDTO;
import org.springframework.stereotype.Component;

@Component
public class LogItemConverter extends Converter<LogItem, LogItemDTO> {
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

    @Override
    @Deprecated
    public LogItem fromDTO(LogItemDTO logItemDTO) {
        return new LogItem();
    }
}
