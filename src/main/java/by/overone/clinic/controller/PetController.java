package by.overone.clinic.controller;

import by.overone.clinic.model.Pet;
import by.overone.clinic.service.PetService;
import lombok.AllArgsConstructor;
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
    public Pet readPet(@PathVariable long id) {return petService.getPetById(id);}

    @GetMapping("/user/{user_id}")
    public List<Pet> readPetsForUser(@RequestParam long user_id) {return null;}

    @PostMapping("/reg-pet")
    public Pet addPet(@RequestBody Pet pet) {return petService.addPet(pet);}

    @GetMapping("/{id}/remove")
    public void removePet(@PathVariable long id) {
    }

    @GetMapping("/{id}/update")
    public void updatePet(@PathVariable long id, @RequestBody Pet pet) {
        petService.updatePet(id, pet);
    }
}

