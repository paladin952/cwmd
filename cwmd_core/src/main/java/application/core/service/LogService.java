package application.core.service;

import application.core.model.logging.LogItem;
import application.core.repository.LogItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class LogService {
    @Autowired
    private LogItemRepository logRepo;

    public List<LogItem> getAll() {
        return logRepo.findAll();
    }

    public List<LogItem> getAll(String filter) {
        return logRepo.getAll(filter);
    }

    public List<LogItem> getAll(Timestamp from, Timestamp to) {
        return logRepo.getAll(from, to);
    }

    public List<LogItem> getAll(Timestamp from, Timestamp to, String filter) {
        return logRepo.getAll(from, to, filter);
    }

    public List<LogItem> getDebug() {
        return logRepo.getDebug();
    }

    public List<LogItem> getInfo() {
        return logRepo.getInfo();
    }

    public List<LogItem> getWarnings() {
        return logRepo.getWarnings();
    }

    public List<LogItem> getErrors() {
        return logRepo.getErrors();
    }

    public List<LogItem> getDebug(Timestamp from, Timestamp to) {
        return logRepo.getDebug(from, to);
    }

    public List<LogItem> getInfo(Timestamp from, Timestamp to) {
        return logRepo.getInfo(from, to);
    }

    public List<LogItem> getWarnings(Timestamp from, Timestamp to) {
        return logRepo.getWarnings(from, to);
    }

    public List<LogItem> getErrors(Timestamp from, Timestamp to) {
        return logRepo.getErrors(from, to);
    }

    public List<LogItem> getDebug(String filter) {
        return logRepo.getDebug(filter);
    }

    public List<LogItem> getInfo(String filter) {
        return logRepo.getInfo(filter);
    }

    public List<LogItem> getWarnings(String filter) {
        return logRepo.getWarnings(filter);
    }

    public List<LogItem> getErrors(String filter) {
        return logRepo.getErrors(filter);
    }

    public List<LogItem> getDebug(Timestamp from, Timestamp to, String filter) {
        return logRepo.getDebug(from, to, filter);
    }

    public List<LogItem> getInfo(Timestamp from, Timestamp to, String filter) {
        return logRepo.getInfo(from, to, filter);
    }

    public List<LogItem> getWarnings(Timestamp from, Timestamp to, String filter) {
        return logRepo.getWarnings(from, to, filter);
    }

    public List<LogItem> getErrors(Timestamp from, Timestamp to, String filter) {
        return logRepo.getErrors(from, to, filter);
    }
}
