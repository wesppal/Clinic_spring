package by.overone.clinic.service.impl;

import by.overone.clinic.dao.UserDao;
import by.overone.clinic.dto.UserDTO;
import by.overone.clinic.dto.UserInfoDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.exception.EntityNotFoundException;
import by.overone.clinic.exception.ExceptionCode;
import by.overone.clinic.model.User;
import by.overone.clinic.model.UserDetails;
import by.overone.clinic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        User user = userDao.getUserById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode()));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getUserByFullName(String name, String surname) {
        List<User> users = userDao.getUserByNameSurname(name, surname);
        if (users.size() < 1) {
            throw new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER_WITH_NAME.getErrorCode());
        }
        return userDao.getUserByNameSurname(name, surname).stream().map(u -> new UserDTO(u.getId(),
                u.getLogin(), u.getEmail())).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public User addUser(UserRegistrationDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user = userDao.addUser(user);
        userDao.addUserDetails(user.getId());
        return user;
    }

    @Override
    public void removeUserById(long id) {
        String status = "DELETED";
        userDao.updateStatus(id, status);
    }

    @Override
    public void updateUserDetails(UserDetails userDetail) {
        userDao.updateUserDetails(userDetail);
    }

    @Override
    public UserInfoDTO getUserDetails(long id) {
        return userDao.getUserDetails(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode()));
    }
}
