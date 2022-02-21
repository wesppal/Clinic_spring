package by.overone.clinic.controller;

import by.overone.clinic.dto.user.UserDTO;
import by.overone.clinic.dto.user.UserDetailsDTO;
import by.overone.clinic.dto.user.UserInfoDTO;
import by.overone.clinic.dto.user.UserRegistrationDTO;
import by.overone.clinic.model.Pet;
import by.overone.clinic.model.User;
import by.overone.clinic.service.PetService;
import by.overone.clinic.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final PetService petService;

    @GetMapping
    public List<UserDTO> readAll(@RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "surname", required = false) String surname) {
        if ((name == null) && (surname == null)) {
            return userService.getAllUsers();
        }
        return userService.getUserByFullName(name, surname);
    }

    @GetMapping("/{id}")
    public UserDTO readUser(@Validated @Min(1) @PathVariable long id) {
        return userService.getUserById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@Validated @RequestBody UserRegistrationDTO userRegistrationDTO) {
        return userService.addUser(userRegistrationDTO);
    }

    @DeleteMapping("/{id}")
    public List<Object> removeUser(@Validated @Min(1) @PathVariable long id) {
        return userService.removeUserById(id);
    }


    @PatchMapping("/{id}/info")
    public UserDetailsDTO updateDetailUser(@Validated @Min(1) @PathVariable long id,
                                           @Validated @RequestBody UserDetailsDTO userDetails) {
        return userService.updateUserDetails(userDetails, id);
    }

    @GetMapping("/{id}/info")
    public UserInfoDTO readInfoUser(@Validated @Min(1) @PathVariable long id) {
        return userService.getUserDetails(id);
    }

    @GetMapping("/{user_id}/pets")
    public List<Pet> readPetsForUser(@Validated @Min(1) @PathVariable long user_id) {
        return petService.getPetByUserId(user_id);
    }

    @PatchMapping("/{id}/verify")
    public UserInfoDTO verifyUser(@Validated @Min(1) @PathVariable long id) {
        return userService.verifyUser(id);
    }
}
