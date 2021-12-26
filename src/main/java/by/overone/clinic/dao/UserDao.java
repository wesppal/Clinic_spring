package by.overone.clinic.dao;

import by.overone.clinic.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> getAllUsers();
    Optional<User> getUserById(long id);
    List<User> getUserByNameSurname(String name, String surname);
    User addUser(User user);
    void removeUserById(long id);
}
