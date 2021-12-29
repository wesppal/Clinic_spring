package by.overone.clinic.controller;

import by.overone.clinic.model.Pet;
import by.overone.clinic.model.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/pets")
public class PetController {

    @GetMapping
    public List<Pet> readAll() {
        return null;
    }

    @GetMapping("/{id}")
    public Pet readPet(@PathVariable long id) {return null;}

    @GetMapping("/user/{user_id}")
    public List<Pet> readPetsForUser(@RequestParam long user_id) {return null;}

    @PostMapping("/reg-pet")
    public User addPet(@RequestBody Pet pet) {return null;}

    @GetMapping("/{id}/remove")
    public void removePet(@PathVariable long id) {
    }

    @GetMapping("/pet-update")
    public void updatePet(@RequestBody Pet Pet) {
    }
}

