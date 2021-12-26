package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.UserDao;
import by.overone.clinic.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final static String GET_ALL_USERS_SQL = "SELECT * FROM user where status != 'deleted'";
    private final static String GET_USER_BY_ID_SQL = "SELECT * FROM user WHERE id=?";
    private final static String GET_USER_BY_FULLNAME = "SELECT * FROM user " +
            "JOIN details on user_id=id where name=? AND surname=?";

    private final JdbcTemplate jdbcTemplate;


    @Override
    public List<User> getAllUsers() {
        List<User> users = jdbcTemplate.query(GET_ALL_USERS_SQL, new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return jdbcTemplate.query(GET_USER_BY_ID_SQL, new Object[]{id},
                new BeanPropertyRowMapper<>(User.class)).stream().findAny();
    }

    @Override
    public Optional<User> getUserByFullName(String name, String surname) {
        return jdbcTemplate.query(GET_USER_BY_FULLNAME, new Object[]{name, surname},
                new BeanPropertyRowMapper<>(User.class)).stream().findAny();
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public void removeUserById(long id) {

    }
}
