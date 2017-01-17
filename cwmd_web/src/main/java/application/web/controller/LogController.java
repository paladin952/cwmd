package application.web.controller;

import application.core.service.LogService;
import application.core.utils.logging.Log;
import application.web.converter.LogItemConverter;
import application.web.dto.LogFilterDataDTO;
import application.web.dto.LogItemDTO;
import application.web.dto.LogTimeIntervalDTO;
import application.web.utils.TimestampConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RequestMapping("/log")
@RestController
public class LogController {
    @Autowired
    private Log log;

    @Autowired
    private LogService logService;

    @Autowired
    private LogItemConverter converter;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<LogItemDTO>> getAll() {
        try {
            return new ResponseEntity<>(converter.toDTOs(logService.getAll()), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/all/filter", method = RequestMethod.GET)
    public ResponseEntity<List<LogItemDTO>> getAll(@RequestBody String filter) {
        try {
            System.out.println(filter);
            return new ResponseEntity<>(converter.toDTOs(logService.getAll(filter)), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/all/timestamps", method = RequestMethod.GET)
    public ResponseEntity<List<LogItemDTO>> getAll(@RequestBody LogTimeIntervalDTO logTimeIntervalDTO) {
        try {
            Timestamp from = TimestampConverter.fromString(logTimeIntervalDTO.getFrom());
            Timestamp to = TimestampConverter.fromString(logTimeIntervalDTO.getTo());

            return new ResponseEntity<>(converter.toDTOs(logService.getAll(from, to)), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/all/all_filters", method = RequestMethod.GET)
    public ResponseEntity<List<LogItemDTO>> getAll(@RequestBody LogFilterDataDTO data) {
        try {
            Timestamp from = TimestampConverter.fromString(data.getInterval().getFrom());
            Timestamp to = TimestampConverter.fromString(data.getInterval().getTo());

            return new ResponseEntity<>(converter.toDTOs(logService.getAll(from, to, data.getFilter())), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/debug", method = RequestMethod.GET)
    public ResponseEntity<List<LogItemDTO>> getDebug() {
        try {
            return new ResponseEntity<>(converter.toDTOs(logService.getDebug()), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application debug log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseEntity<List<LogItemDTO>> getInfo() {
        try {
            return new ResponseEntity<>(converter.toDTOs(logService.getInfo()), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application debug log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/warn", method = RequestMethod.GET)
    public ResponseEntity<List<LogItemDTO>> getWarnings() {
        try {
            return new ResponseEntity<>(converter.toDTOs(logService.getWarnings()), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application warnings log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ResponseEntity<List<LogItemDTO>> getErrors() {
        try {
            return new ResponseEntity<>(converter.toDTOs(logService.getErrors()), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Ironic error while retrieving application error log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/debug/filter", method = RequestMethod.GET)
    public ResponseEntity<List<LogItemDTO>> getDebug(@RequestBody String filter) {
        try {
            return new ResponseEntity<>(converter.toDTOs(logService.getDebug(filter)), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application debug log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/info/filter", method = RequestMethod.GET)
    public ResponseEntity<List<LogItemDTO>> getInfo(@RequestBody String filter) {
        try {
            return new ResponseEntity<>(converter.toDTOs(logService.getInfo(filter)), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application info log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/warn/filter", method = RequestMethod.GET)
    public ResponseEntity<List<LogItemDTO>> getWarnings(@RequestBody String filter) {
        try {
            return new ResponseEntity<>(converter.toDTOs(logService.getWarnings(filter)), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application warnings log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/error/filter", method = RequestMethod.GET)
    public ResponseEntity<List<LogItemDTO>> getErrors(@RequestBody String filter) {
        try {
            return new ResponseEntity<>(converter.toDTOs(logService.getErrors(filter)), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Ironic error while retrieving application error log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/debug/timestamps", method = RequestMethod.GET)
    public ResponseEntity<List<LogItemDTO>> getDebug(@RequestBody LogTimeIntervalDTO timeIntervalDTO) {
        try {
            Timestamp from = TimestampConverter.fromString(timeIntervalDTO.getFrom());
            Timestamp to = TimestampConverter.fromString(timeIntervalDTO.getTo());

            return new ResponseEntity<>(converter.toDTOs(logService.getDebug(from, to)), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application debug log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/info/timestamps", method = RequestMethod.GET)
    public ResponseEntity<List<LogItemDTO>> getInfo(@RequestBody LogTimeIntervalDTO timeIntervalDTO) {
        try {
            Timestamp from = TimestampConverter.fromString(timeIntervalDTO.getFrom());
            Timestamp to = TimestampConverter.fromString(timeIntervalDTO.getTo());
            return new ResponseEntity<>(converter.toDTOs(logService.getInfo(from, to)), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application info log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/warn/timestamps", method = RequestMethod.GET)
    public ResponseEntity<List<LogItemDTO>> getWarnings(@RequestBody LogTimeIntervalDTO timeIntervalDTO) {
        try {
            Timestamp from = TimestampConverter.fromString(timeIntervalDTO.getFrom());
            Timestamp to = TimestampConverter.fromString(timeIntervalDTO.getTo());
            return new ResponseEntity<>(converter.toDTOs(logService.getWarnings(from, to)), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application warnings log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/error/timestamps", method = RequestMethod.GET)
    public ResponseEntity<List<LogItemDTO>> getErrors(@RequestBody LogTimeIntervalDTO timeIntervalDTO) {
        try {
            Timestamp from = TimestampConverter.fromString(timeIntervalDTO.getFrom());
            Timestamp to = TimestampConverter.fromString(timeIntervalDTO.getTo());
            return new ResponseEntity<>(converter.toDTOs(logService.getErrors(from, to)), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Ironic error while retrieving application error log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/debug/all_filters", method = RequestMethod.GET)
    public ResponseEntity<List<LogItemDTO>> getDebug(@RequestBody LogFilterDataDTO data) {
        try {
            Timestamp from = TimestampConverter.fromString(data.getInterval().getFrom());
            Timestamp to = TimestampConverter.fromString(data.getInterval().getTo());
            return new ResponseEntity<>(converter.toDTOs(logService.getDebug(from, to, data.getFilter())), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application debug log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/info/all_filters", method = RequestMethod.GET)
    public ResponseEntity<List<LogItemDTO>> getInfo(@RequestBody LogFilterDataDTO data) {
        try {
            Timestamp from = TimestampConverter.fromString(data.getInterval().getFrom());
            Timestamp to = TimestampConverter.fromString(data.getInterval().getTo());
            return new ResponseEntity<>(converter.toDTOs(logService.getInfo(from, to, data.getFilter())), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application info log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/warn/all_filters", method = RequestMethod.GET)
    public ResponseEntity<List<LogItemDTO>> getWarnings(@RequestBody LogFilterDataDTO data) {
        try {
            Timestamp from = TimestampConverter.fromString(data.getInterval().getFrom());
            Timestamp to = TimestampConverter.fromString(data.getInterval().getTo());
            return new ResponseEntity<>(converter.toDTOs(logService.getWarnings(from, to, data.getFilter())), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application warnings log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/error/all_filters", method = RequestMethod.GET)
    public ResponseEntity<List<LogItemDTO>> getErrors(@RequestBody LogFilterDataDTO data) {
        try {
            Timestamp from = TimestampConverter.fromString(data.getInterval().getFrom());
            Timestamp to = TimestampConverter.fromString(data.getInterval().getTo());
            return new ResponseEntity<>(converter.toDTOs(logService.getErrors(from, to, data.getFilter())), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Ironic error while retrieving application error log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
