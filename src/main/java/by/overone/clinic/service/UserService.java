package by.overone.clinic.service;

import by.overone.clinic.dto.UserDTO;
import by.overone.clinic.dto.UserInfoDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.model.User;
import by.overone.clinic.model.UserDetail;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(long id);
    List<UserDTO> getUserByFullName (String name, String surname);
    User addUser(UserRegistrationDTO user);
    void removeUserById(long id);
    void updateUserDetails(UserDetail userDetail);
    UserInfoDTO getUserDetails(long id);
}
