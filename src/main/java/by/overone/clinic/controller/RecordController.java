package by.overone.clinic.controller;

import by.overone.clinic.dao.RecordDao;
import by.overone.clinic.dto.rec.RecordDTO;
import by.overone.clinic.exception.EntityNotFoundException;
import by.overone.clinic.exception.ExceptionCode;
import by.overone.clinic.exception.FaultInDateException;
import by.overone.clinic.model.Record;
import by.overone.clinic.service.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/records")
public class RecordController {
    private final RecordDao recordDao;
    private final RecordService recordService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Record addRecord(@Validated @RequestBody Record record) {
        if (record.getAdmission_date().getHour() >= 17 || record.getAdmission_date().getHour() < 8) {
            throw new FaultInDateException(ExceptionCode.NOT_MISMATCH_HOURS_IN_RECORD.getErrorCode());
        }
        if (record.getAdmission_date().getMinute() != 0 && record.getAdmission_date().getMinute() != 30) {
            throw new FaultInDateException(ExceptionCode.NOT_MISMATCH_MINUTES_IN_RECORD.getErrorCode());
        }
        List<Record> records = recordDao.getAllRecords();

        Record rec = records.stream().filter(r -> r.getAdmission_date().compareTo(record.getAdmission_date()) == 0).findAny()
                .orElse(null);

        if (rec != null) {
            throw new FaultInDateException(ExceptionCode.ALREADY_EXISTING_RECORD.getErrorCode());
        }
        return recordDao.addRecord(record);
    }

    @GetMapping
    public List<RecordDTO> getRecords() {
        return recordService.getAllRecords();
    }
}
