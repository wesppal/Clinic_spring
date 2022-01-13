package by.overone.clinic.controller;

import by.overone.clinic.dto.UserDTO;
import by.overone.clinic.dto.UserInfoDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.exception.EntityNotFoundException;
import by.overone.clinic.exception.ExceptionCode;
import by.overone.clinic.model.Pet;
import by.overone.clinic.model.User;
import by.overone.clinic.model.UserDetails;
import by.overone.clinic.service.PetService;
import by.overone.clinic.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        return userService.addUser(userRegistrationDTO);
    }

    @PatchMapping("/{id}/remove")
    public void removeUser(@PathVariable long id) {
        userService.removeUserById(id);
    }


    @PatchMapping("/{id}/info")
    public void updateDetailUser(@PathVariable long id, @RequestBody UserDetails userDetail) {
        if (userDetail.getUser_id() == 0) {
            userDetail.setUser_id(id);
        }
        if (userDetail.getUser_id() == id) {
            userService.updateUserDetails(userDetail);
        } else {
            throw new EntityNotFoundException(ExceptionCode.NOT_MISMATCH_USER_ID.getErrorCode());
        }
    }

    @GetMapping("/{id}/info")
    public UserInfoDTO readInfoUser(@PathVariable long id) {
        return userService.getUserDetails(id);
    }

    @GetMapping("/{user_id}/pets")
    public List<Pet> readPetsForUser(@PathVariable long user_id) {
        return petService.getPetByUserId(user_id);
    }
}
