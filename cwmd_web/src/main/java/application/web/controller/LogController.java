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
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

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

    @RequestMapping(value = "/all/filter", method = RequestMethod.POST)
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

    @RequestMapping(value = "/all/timestamps", method = RequestMethod.POST)
    public ResponseEntity<List<LogItemDTO>> getAll(@RequestBody LogTimeIntervalDTO logTimeIntervalDTO) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.UK);
            format.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date dateFrom = format.parse(logTimeIntervalDTO.getFrom());
            Date dateTo = format.parse(logTimeIntervalDTO.getTo());
            Timestamp from = new Timestamp(dateFrom.getTime());
            Timestamp to = new Timestamp(dateTo.getTime());

            return new ResponseEntity<>(converter.toDTOs(logService.getAll(from, to)), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/all/all_filters", method = RequestMethod.POST)
    public ResponseEntity<List<LogItemDTO>> getAll(@RequestBody LogFilterDataDTO data) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.UK);
            format.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date dateFrom = format.parse(data.getInterval().getFrom());
            Date dateTo = format.parse(data.getInterval().getTo());
            Timestamp from = new Timestamp(dateFrom.getTime());
            Timestamp to = new Timestamp(dateTo.getTime());

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

    @RequestMapping(value = "/debug/filter", method = RequestMethod.POST)
    public ResponseEntity<List<LogItemDTO>> getDebug(@RequestBody String filter) {
        try {
            return new ResponseEntity<>(converter.toDTOs(logService.getDebug(filter)), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application debug log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/info/filter", method = RequestMethod.POST)
    public ResponseEntity<List<LogItemDTO>> getInfo(@RequestBody String filter) {
        try {
            System.out.println(filter);
            return new ResponseEntity<>(converter.toDTOs(logService.getInfo(filter)), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application info log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/warn/filter", method = RequestMethod.POST)
    public ResponseEntity<List<LogItemDTO>> getWarnings(@RequestBody String filter) {
        try {
            return new ResponseEntity<>(converter.toDTOs(logService.getWarnings(filter)), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application warnings log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/error/filter", method = RequestMethod.POST)
    public ResponseEntity<List<LogItemDTO>> getErrors(@RequestBody String filter) {
        try {
            return new ResponseEntity<>(converter.toDTOs(logService.getErrors(filter)), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Ironic error while retrieving application error log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/debug/timestamps", method = RequestMethod.POST)
    public ResponseEntity<List<LogItemDTO>> getDebug(@RequestBody LogTimeIntervalDTO logTimeIntervalDTO) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.UK);
            format.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date dateFrom = format.parse(logTimeIntervalDTO.getFrom());
            Date dateTo = format.parse(logTimeIntervalDTO.getTo());
            Timestamp from = new Timestamp(dateFrom.getTime());
            Timestamp to = new Timestamp(dateTo.getTime());

            return new ResponseEntity<>(converter.toDTOs(logService.getDebug(from, to)), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application debug log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/info/timestamps", method = RequestMethod.POST)
    public ResponseEntity<List<LogItemDTO>> getInfo(@RequestBody LogTimeIntervalDTO logTimeIntervalDTO) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.UK);
            format.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date dateFrom = format.parse(logTimeIntervalDTO.getFrom());
            Date dateTo = format.parse(logTimeIntervalDTO.getTo());
            Timestamp from = new Timestamp(dateFrom.getTime());
            Timestamp to = new Timestamp(dateTo.getTime());
            return new ResponseEntity<>(converter.toDTOs(logService.getInfo(from, to)), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application info log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/warn/timestamps", method = RequestMethod.POST)
    public ResponseEntity<List<LogItemDTO>> getWarnings(@RequestBody LogTimeIntervalDTO logTimeIntervalDTO) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.UK);
            format.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date dateFrom = format.parse(logTimeIntervalDTO.getFrom());
            Date dateTo = format.parse(logTimeIntervalDTO.getTo());
            Timestamp from = new Timestamp(dateFrom.getTime());
            Timestamp to = new Timestamp(dateTo.getTime());
            return new ResponseEntity<>(converter.toDTOs(logService.getWarnings(from, to)), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application warnings log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/error/timestamps", method = RequestMethod.POST)
    public ResponseEntity<List<LogItemDTO>> getErrors(@RequestBody LogTimeIntervalDTO logTimeIntervalDTO) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.UK);
            format.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date dateFrom = format.parse(logTimeIntervalDTO.getFrom());
            Date dateTo = format.parse(logTimeIntervalDTO.getTo());
            Timestamp from = new Timestamp(dateFrom.getTime());
            Timestamp to = new Timestamp(dateTo.getTime());
            return new ResponseEntity<>(converter.toDTOs(logService.getErrors(from, to)), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Ironic error while retrieving application error log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/debug/all_filters", method = RequestMethod.POST)
    public ResponseEntity<List<LogItemDTO>> getDebug(@RequestBody LogFilterDataDTO data) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.UK);
            format.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date dateFrom = format.parse(data.getInterval().getFrom());
            Date dateTo = format.parse(data.getInterval().getTo());
            Timestamp from = new Timestamp(dateFrom.getTime());
            Timestamp to = new Timestamp(dateTo.getTime());
            return new ResponseEntity<>(converter.toDTOs(logService.getDebug(from, to, data.getFilter())), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application debug log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/info/all_filters", method = RequestMethod.POST)
    public ResponseEntity<List<LogItemDTO>> getInfo(@RequestBody LogFilterDataDTO data) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.UK);
            format.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date dateFrom = format.parse(data.getInterval().getFrom());
            Date dateTo = format.parse(data.getInterval().getTo());
            Timestamp from = new Timestamp(dateFrom.getTime());
            Timestamp to = new Timestamp(dateTo.getTime());
            return new ResponseEntity<>(converter.toDTOs(logService.getInfo(from, to, data.getFilter())), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application info log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/warn/all_filters", method = RequestMethod.POST)
    public ResponseEntity<List<LogItemDTO>> getWarnings(@RequestBody LogFilterDataDTO data) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.UK);
            format.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date dateFrom = format.parse(data.getInterval().getFrom());
            Date dateTo = format.parse(data.getInterval().getTo());
            Timestamp from = new Timestamp(dateFrom.getTime());
            Timestamp to = new Timestamp(dateTo.getTime());            return new ResponseEntity<>(converter.toDTOs(logService.getWarnings(from, to, data.getFilter())), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Error while retrieving application warnings log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/error/all_filters", method = RequestMethod.POST)
    public ResponseEntity<List<LogItemDTO>> getErrors(@RequestBody LogFilterDataDTO data) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.UK);
            format.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date dateFrom = format.parse(data.getInterval().getFrom());
            Date dateTo = format.parse(data.getInterval().getTo());
            Timestamp from = new Timestamp(dateFrom.getTime());
            Timestamp to = new Timestamp(dateTo.getTime());
            return new ResponseEntity<>(converter.toDTOs(logService.getErrors(from, to, data.getFilter())), HttpStatus.OK);
        } catch (Exception e) {
            log.error(LogController.class.getSimpleName(), "Ironic error while retrieving application error log", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
