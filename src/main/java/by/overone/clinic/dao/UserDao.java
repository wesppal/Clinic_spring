package by.overone.clinic.dao;

import by.overone.clinic.dto.UserInfoDTO;
import by.overone.clinic.model.User;
import by.overone.clinic.model.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> getAllUsers();

    Optional<User> getUserById(long id);

    List<User> getUserByNameSurname(String name, String surname);

    User addUser(User user);

    void addUserDetails(long id);

    void updateStatus(long id, String status);

    UserDetails updateUserDetails(UserDetails userDetail);

    Optional<UserInfoDTO> getUserDetails(long id);
}
