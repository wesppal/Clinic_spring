package by.overone.clinic.controller;

import by.overone.clinic.dto.UserDTO;
import by.overone.clinic.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDTO> readAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/hello")
    public String read() {
        return "hello";
    }

    @GetMapping("/{id}")
    public UserDTO readUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/search")
    public UserDTO readUser(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname) {
        return userService.getUserByFullName(name, surname);
    }
}
