package by.overone.clinic.controller;

import by.overone.clinic.dao.RecordDao;
import by.overone.clinic.model.Record;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/records")
public class RecordController {
    private final RecordDao recordDao;


    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Record addRecord(@Validated @RequestBody Record record) {
        return recordDao.addRecord(record);
    }
}
