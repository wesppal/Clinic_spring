package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.UserDao;
import by.overone.clinic.dto.UserInfoDTO;
import by.overone.clinic.model.User;
import by.overone.clinic.model.UserDetails;
import by.overone.clinic.util.*;
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

    private final static String GET_ALL_USERS_SQL = "SELECT * FROM " + UserConst.TABLE_NAME +
            " WHERE " + UserConst.STATUS + " = '" + status + "'";
    private final static String GET_USER_BY_ID_SQL = "SELECT * FROM " + UserConst.TABLE_NAME +
            " WHERE " + UserConst.ID + "=? AND " + UserConst.STATUS + " = '" + status + "'";
    private final static String GET_USER_BY_NAME_SURNAME_SQL = "SELECT * FROM " + UserConst.TABLE_NAME +
            " JOIN " + UserDetailConst.TABLE_NAME + " on " + UserDetailConst.ID + "=" + UserConst.ID;
    private final static String ADD_ID_BY_DETAIL_SQL = "INSERT INTO " + UserDetailConst.TABLE_NAME +
            " SET " + UserDetailConst.ID + "=?";
    private final static String UPDATE_USER_STATUS_SQL = "UPDATE " + UserConst.TABLE_NAME +
            " SET " + UserConst.STATUS + " =? WHERE " + UserConst.ID + "=?";
    private final static String UPDATE_USER_DETAILS_SQL = "UPDATE " + UserDetailConst.TABLE_NAME + " SET " +
            UserDetailConst.NAME + "=COALESCE(?," + UserDetailConst.NAME + "), " +
            UserDetailConst.SURNAME + "=COALESCE(?," + UserDetailConst.SURNAME + "), " +
            UserDetailConst.ADDRESS + "=COALESCE(?," + UserDetailConst.ADDRESS + "), " +
            UserDetailConst.PHONENUMBER + "=COALESCE(?," + UserDetailConst.PHONENUMBER + ") " +
            "WHERE " + UserDetailConst.ID + " = ?";
    private final static String GET_ALL_INFO_USER_BY_ID_SQL = "SELECT " + UserConst.ID + ", " + UserConst.LOGIN + ", " +
            UserConst.EMAIL + "," + UserConst.ROLE + "," + UserDetailConst.NAME + "," + UserDetailConst.SURNAME + "," +
            UserDetailConst.ADDRESS + "," + UserDetailConst.PHONENUMBER + " FROM " + UserDetailConst.TABLE_NAME +
            " JOIN " + UserConst.TABLE_NAME + " on " + UserConst.TABLE_NAME + "." + UserConst.ID + "=" +
            UserDetailConst.TABLE_NAME + "." + UserDetailConst.ID + " WHERE " + UserDetailConst.ID + " = ?";

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    @Qualifier("user")
    private SimpleJdbcInsert simpleJdbcInsert;


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
            param += " where " + UserDetailConst.NAME + " = ?";
            if (surname != null) {
                param += " AND " + UserDetailConst.SURNAME + " = ?";
                return jdbcTemplate.query(GET_USER_BY_NAME_SURNAME_SQL + param, new Object[]{name, surname},
                        new BeanPropertyRowMapper<>(User.class));
            }
            return jdbcTemplate.query(GET_USER_BY_NAME_SURNAME_SQL + param, new Object[]{name},
                    new BeanPropertyRowMapper<>(User.class));
        }
        if (surname != null) {
            param += " where " + UserDetailConst.SURNAME + " = ?";
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
    public void updateStatus(long id, Enum<Status> stat) {
        String status = stat.toString();
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
