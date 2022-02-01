package by.overone.clinic.controller;

import by.overone.clinic.dao.RecordDao;
import by.overone.clinic.dto.user.UserDTO;
import by.overone.clinic.model.Record;
import by.overone.clinic.service.PetService;
import by.overone.clinic.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/doctors")
public class DoctorController {
    private final UserService userService;
    private final PetService petService;
    private final RecordDao recordDao;


    @GetMapping("/{id}/records")
    public List<Record> getRecord(@PathVariable long id) {
        return recordDao.getRecordByDoctor(id);
    }
}
