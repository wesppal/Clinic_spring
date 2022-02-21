package by.overone.clinic.controller;

import by.overone.clinic.dto.rec.RecordDTO;
import by.overone.clinic.service.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/doctors")
public class DoctorController {
    private final RecordService recordService;


    @GetMapping("/{id}/records")
    public List<RecordDTO> getRecord(@PathVariable long id) {
        return recordService.getRecordsByDoctor(id);
    }
}
