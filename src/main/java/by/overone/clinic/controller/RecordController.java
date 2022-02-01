package by.overone.clinic.controller;

import by.overone.clinic.dao.RecordDao;
import by.overone.clinic.model.Record;
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


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Record addRecord(@Validated @RequestBody Record record) {
        System.out.println(record.getAdmission_date().toString());
        if (record.getAdmission_date().getHour() >= 17 || record.getAdmission_date().getHour() < 8) {
            System.out.println("error hours");
            return null;
        }
        if (record.getAdmission_date().getMinute() != 0 && record.getAdmission_date().getMinute() != 30) {
            System.out.println("error minutes");
            return null;
        }
        List<Record> records = recordDao.getAllRecords();

        Record rec = records.stream().filter(r -> r.getAdmission_date().compareTo(record.getAdmission_date()) == 0).findAny()
                .orElse(null);

        if (rec != null) {
            System.out.println("time atata");
            return null;
        }
        return recordDao.addRecord(record);
    }

    @GetMapping
    public List<Record> getRecords() {
        return recordDao.getAllRecords();
    }
}
