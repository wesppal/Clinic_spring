package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.UserDao;
import by.overone.clinic.dto.UserInfoDTO;
import by.overone.clinic.model.User;
import by.overone.clinic.model.UserDetails;
import by.overone.clinic.util.Role;
import by.overone.clinic.util.Status;
import by.overone.clinic.util.UserConst;
import com.fasterxml.jackson.annotation.JacksonInject;
import jdk.jfr.Name;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private static String status = Status.ACTIVE.toString();

    private final static String GET_ALL_USERS_SQL = "SELECT * FROM user where status = '" + status + "'";
    private final static String GET_USER_BY_ID_SQL = "SELECT * FROM user WHERE id=? AND status = '" + status + "'";
    private final static String GET_USER_BY_NAME_SURNAME_SQL = "SELECT * FROM user JOIN user_details on user_id=id";
    private final static String ADD_ID_BY_DETAIL_SQL = "INSERT INTO user_details Set user_id=?";
    private final static String UPDATE_USER_STATUS_SQL = "UPDATE user SET status =? WHERE id=?";
    private final static String UPDATE_USER_DETAILS_SQL = "UPDATE user_details SET " +
            "name=COALESCE(?,name), " +
            "surname=COALESCE(?,surname), " +
            "address=COALESCE(?,address), " +
            "phoneNumber=COALESCE(?,phoneNumber) " +
            "WHERE user_id = ?";
    private final static String GET_ALL_INFO_USER_BY_ID_SQL = "SELECT id, login, email,name,surname,address," +
            "phoneNumber FROM user_details JOIN user on user.id=user_details.user_id " +
            "WHERE user_id = ?";

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    @Qualifier("user")
    private SimpleJdbcInsert simpleJdbcInsert;


    @Override
    public List<User> getAllUsers() {
        status = Status.ACTIVE.toString();
        return jdbcTemplate.query(GET_ALL_USERS_SQL, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public Optional<User> getUserById(long id) {
        return jdbcTemplate.query(GET_USER_BY_ID_SQL, new Object[]{id},
                new BeanPropertyRowMapper<>(User.class)).stream().findAny();
    }

    @Override
    public List<User> getUserByNameSurname(String name, String surname) {
        String param = "";
        if (name != null) {
            param += " where name = ?";
            if (surname != null) {
                param += " AND surname = ?";
                return jdbcTemplate.query(GET_USER_BY_NAME_SURNAME_SQL + param, new Object[]{name, surname},
                        new BeanPropertyRowMapper<>(User.class));
            }
            return jdbcTemplate.query(GET_USER_BY_NAME_SURNAME_SQL + param, new Object[]{name},
                    new BeanPropertyRowMapper<>(User.class));
        }
        if (surname != null) {
            param += " where surname = ?";
            return jdbcTemplate.query(GET_USER_BY_NAME_SURNAME_SQL + param, new Object[]{surname},
                    new BeanPropertyRowMapper<>(User.class));
        }
        return jdbcTemplate.query(GET_USER_BY_NAME_SURNAME_SQL + param, new Object[]{},
                new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User addUser(User user) {
        user.setRole(Role.USER.toString());
        user.setStatus(Status.VERIFY.toString());
        Number id = simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(user));
        user.setId(id.longValue());
        return user;
    }

    @Override
    public void addUserDetails(long user_id) {
        jdbcTemplate.update(ADD_ID_BY_DETAIL_SQL, user_id);
    }


    @Override
    public void updateStatus(long id, String status) {
        jdbcTemplate.update(UPDATE_USER_STATUS_SQL, status, id);
    }

    @Override
    public UserDetails updateUserDetails(UserDetails userDetail) {
        jdbcTemplate.update(UPDATE_USER_DETAILS_SQL, userDetail.getName(), userDetail.getSurname(),
                userDetail.getAddress(), userDetail.getPhoneNumber(), userDetail.getUser_id());
        return userDetail;
    }

    @Override
    public Optional<UserInfoDTO> getUserDetails(long id) {
        return jdbcTemplate.query(GET_ALL_INFO_USER_BY_ID_SQL, new Object[]{id},
                new BeanPropertyRowMapper<>(UserInfoDTO.class)).stream().findAny();
    }

}
