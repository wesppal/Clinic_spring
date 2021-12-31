package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.UserDao;
import by.overone.clinic.dto.UserInfoDTO;
import by.overone.clinic.model.User;
import by.overone.clinic.model.UserDetail;
import by.overone.clinic.util.UserConst;
import lombok.RequiredArgsConstructor;
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

    private final static String GET_ALL_USERS_SQL = "SELECT * FROM user where status != 'deleted'";
    private final static String GET_USER_BY_ID_SQL = "SELECT * FROM user WHERE id=?";
    private final static String GET_USER_BY_NAME_SURNAME_SQL = "SELECT * FROM user JOIN details on user_id=id";
    private final static String ADD_ID_BY_DETAIL_SQL = "INSERT INTO details user_id VALUES ?";
    private final static String UPDATE_USER_STATUS_SQL = "UPDATE user SET status =? WHERE id=?";
    private final static String UPDATE_USER_DETAILS_SQL = "UPDATE details SET name = ?, surname = ?, address = ?, " +
            "phoneNumber = ? WHERE user_id = ?";
    private final static String GET_ALL_INFO_USER_BY_ID_SQL = "SELECT id, login, email, details.name, " +
            "details.surname, details.address, details.phoneNumber FROM user JOIN details on details.user_id=user.id " +
            "WHERE id = ? AND status != 'deleted'";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;


    @Override
    public List<User> getAllUsers() {
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
        user.setRole("USER");
        user.setStatus("VERIFY");

        simpleJdbcInsert.withTableName("user").usingGeneratedKeyColumns(UserConst.ID);
        Number id = simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(user));
        jdbcTemplate.update(ADD_ID_BY_DETAIL_SQL, id.longValue());
        return user;
    }

    @Override
    public void removeUserById(long id) {
        String status = "DELETED";
        jdbcTemplate.update(UPDATE_USER_STATUS_SQL, status, id);
    }

    @Override
    public void updateUserDetails(UserDetail userDetail) {
        UserInfoDTO userTemp = allInfoUser(userDetail.getUser_id()).orElseThrow();

        if (userTemp.getName() != null) {
            if (userDetail.getName() == null) {
                userDetail.setName(userTemp.getName());
            }
        }
        if (userTemp.getSurname() != null) {
            if (userDetail.getSurname() == null) {
                userDetail.setSurname(userTemp.getSurname());
            }
        }
        if (userTemp.getAddress() != null) {
            if (userDetail.getAddress() == null) {
                userDetail.setAddress(userTemp.getAddress());
            }
        }
        if (userTemp.getPhoneNumber() != null) {
            if (userDetail.getPhoneNumber() == null) {
                userDetail.setPhoneNumber(userTemp.getPhoneNumber());
            }
        }
        jdbcTemplate.update(UPDATE_USER_DETAILS_SQL, userDetail.getName(), userDetail.getSurname(),
                userDetail.getAddress(), userDetail.getPhoneNumber(), userDetail.getUser_id());
    }

    @Override
    public Optional<UserInfoDTO> allInfoUser(long id) {
        return jdbcTemplate.query(GET_ALL_INFO_USER_BY_ID_SQL, new Object[]{id},
                new BeanPropertyRowMapper<>(UserInfoDTO.class)).stream().findAny();
    }

}
