package by.overone.clinic.service;

import by.overone.clinic.dto.user.UserDTO;
import by.overone.clinic.dto.user.UserRegistrationDTO;
import by.overone.clinic.model.User;
import by.overone.clinic.model.UserDetails;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserById(long id);

    List<UserDTO> getUserByFullName(String name, String surname);

    User addUser(UserRegistrationDTO user);

    UserDetails removeUserById(long id);

    UserDetails updateUserDetails(UserDetails userDetail);

    UserDetails getUserDetails(long id);

    UserDetails verifyUser(long id);
}
