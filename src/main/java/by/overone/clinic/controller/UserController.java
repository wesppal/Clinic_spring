package by.overone.clinic.controller;

import by.overone.clinic.dto.UserDTO;
import by.overone.clinic.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{name}+{surname}")
    public UserDTO readUser(@PathVariable String name, @PathVariable String surname) {
        return userService.getUserByFullName(name, surname);
    }
}
