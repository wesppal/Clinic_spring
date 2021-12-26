package by.overone.clinic.service.impl;

import by.overone.clinic.dao.UserDao;
import by.overone.clinic.dto.UserDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.model.User;
import by.overone.clinic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final ModelMapper modelMapper;

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = userDao.getAllUsers().stream().map(u -> new UserDTO(u.getId(), u.getLogin(),
                u.getEmail())).collect(Collectors.toList());
        return users;
    }

    @Override
    public UserDTO getUserById(long id) {
        User user = userDao.getUserById(id).orElseThrow(() -> new RuntimeException());
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getUserByFullName(String name, String surname) {
        List<UserDTO> users = userDao.getUserByNameSurname(name, surname).stream().map(u -> new UserDTO(u.getId(),
                u.getLogin(), u.getEmail())).collect(Collectors.toList());
        return users;
    }

    @Override
    public User addUser(UserRegistrationDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user = userDao.addUser(user);
        return user;
    }
}
