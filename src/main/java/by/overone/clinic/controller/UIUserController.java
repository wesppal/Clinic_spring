package by.overone.clinic.controller;

import by.overone.clinic.dto.user.UserDTO;
import by.overone.clinic.service.PetService;
import by.overone.clinic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UIUserController {
    private final UserService userService;
    private final PetService petService;

    @GetMapping
    public String readAll(Model model) {

        List<UserDTO> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
