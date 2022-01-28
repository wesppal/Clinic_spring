package by.overone.clinic.dao;

import by.overone.clinic.dto.user.UserInfoDTO;
import by.overone.clinic.model.User;
import by.overone.clinic.model.UserDetails;
import by.overone.clinic.util.Status;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> getAllUsers();

    User getUserById(long id);

    List<User> getUserByNameSurname(String name, String surname);

    User addUser(User user);

    void updateStatus(long id, Status stat);

    UserDetails updateUserDetails(UserDetails userDetail);

    UserDetails getUserDetails(long id);
}
