package by.overone.clinic.service.impl;

import by.overone.clinic.dao.UserDao;
import by.overone.clinic.dto.UserDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.model.User;
import by.overone.clinic.model.UserDetail;
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
        return userDao.getAllUsers().stream().map(u -> new UserDTO(u.getId(), u.getLogin(),
                u.getEmail())).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(long id) {
        User user = userDao.getUserById(id).orElseThrow(RuntimeException::new);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getUserByFullName(String name, String surname) {
        return userDao.getUserByNameSurname(name, surname).stream().map(u -> new UserDTO(u.getId(),
                u.getLogin(), u.getEmail())).collect(Collectors.toList());
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

    @Override
    public void removeUserById(long id) {
        if (id > 0){
            userDao.removeUserById(id);
        }
    }

    @Override
    public void updateUserDetails(UserDetail userDetail) {
        userDao.updateUserDetails(userDetail);
    }
}
