package by.overone.clinic.service;

import by.overone.clinic.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(long id);
    UserDTO getUserByFullName (String name, String surname);
}
