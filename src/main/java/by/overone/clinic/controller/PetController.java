package by.overone.clinic.controller;

import by.overone.clinic.dao.RecordDao;
import by.overone.clinic.model.Pet;
import by.overone.clinic.model.Record;
import by.overone.clinic.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;
    private final RecordDao recordDao;

    @GetMapping
    public List<Pet> readAll() {
        return petService.getPets();
    }

    @GetMapping("/{id}")
    public Pet readPet(@PathVariable long id) {
        return petService.getPetById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pet addPet(@RequestBody Pet pet) {
        return petService.addPet(pet);
    }

    @PatchMapping("/{id}")
    public Pet updatePet(@PathVariable long id, @RequestBody Pet pet) {
            pet.setPet_id(id);
            return petService.updatePet(pet);
    }

    @DeleteMapping("/{id}")
    public Pet removePet(@PathVariable long id) {
        return petService.removePetById(id);
    }

    @GetMapping("/{id}/verify")
    public Pet verifyPet(@PathVariable long id) {
        return petService.verifyPet(id);
    }

    @GetMapping("/{id}/records")
    public List<Record> getRecords(@PathVariable long id) {
        return recordDao.getRecordsByPet(id);
    }
}

