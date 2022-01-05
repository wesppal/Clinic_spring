package by.overone.clinic.controller;

import by.overone.clinic.dto.UserDTO;
import by.overone.clinic.dto.UserInfoDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.model.Pet;
import by.overone.clinic.model.User;
import by.overone.clinic.model.UserDetail;
import by.overone.clinic.service.PetService;
import by.overone.clinic.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final PetService petService;

    @GetMapping
    public List<UserDTO> readAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO readUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/")
    public List<UserDTO> readUser(@RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "surname", required = false) String surname) {
        return userService.getUserByFullName(name, surname);
    }

    @PostMapping("/reg")
    public User addUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        return userService.addUser(userRegistrationDTO);
    }

    @GetMapping("/{id}/remove")
    public void removeUser(@PathVariable long id) {
        userService.removeUserById(id);
    }


    @GetMapping("/det-update")
    public void updateDetailUser(@RequestBody UserDetail userDetail) {
        userService.updateUserDetails(userDetail);
    }

    @GetMapping("/{id}/info")
    public UserInfoDTO readInfoUser(@PathVariable long id) {
        return userService.allInfoUser(id);
    }

    @GetMapping("/{user_id}/pets")
    public List<Pet> readPetsForUser(@PathVariable long user_id) {
        return petService.getPetByUserId(user_id);
    }
}
