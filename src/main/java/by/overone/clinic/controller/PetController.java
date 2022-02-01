package by.overone.clinic.controller;

import by.overone.clinic.exception.EntityNotFoundException;
import by.overone.clinic.exception.ExceptionCode;
import by.overone.clinic.model.Pet;
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
        if (pet.getPet_id() == 0) {
            pet.setPet_id(id);
        }
        if (pet.getPet_id() == id) {
            return petService.updatePet(pet);
        } else {
            throw new EntityNotFoundException(ExceptionCode.NOT_MISMATCH_USER_ID.getErrorCode());
        }
    }

    @DeleteMapping("/{id}")
    public Pet removePet(@PathVariable long id) {
        return petService.removePetById(id);
    }

    @GetMapping("/{id}/verify")
    public Pet verifyPet(@PathVariable long id) {
        return petService.verifyPet(id);
    }
}

