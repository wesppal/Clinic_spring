package by.overone.clinic.service;

import by.overone.clinic.dto.UserDTO;
import by.overone.clinic.dto.UserInfoDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.model.User;
import by.overone.clinic.model.UserDetails;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(long id);
    List<UserDTO> getUserByFullName (String name, String surname);
    User addUser(UserRegistrationDTO user);
    void removeUserById(long id);
    void updateUserDetails(UserDetails userDetail);
    UserInfoDTO getUserDetails(long id);
}
