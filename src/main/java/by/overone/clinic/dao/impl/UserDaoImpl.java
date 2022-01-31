package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.UserDao;
import by.overone.clinic.dto.user.UserInfoDTO;
import by.overone.clinic.model.User;
import by.overone.clinic.model.UserDetails;
import by.overone.clinic.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private static String status = Status.DELETED.toString();

    private final static String GET_ALL_USERS_SQL = "SELECT * FROM " + UserConst.TABLE_NAME +
            " WHERE " + UserConst.STATUS + " != '" + status + "'";
    private final static String GET_USER_BY_ID_SQL = "SELECT * FROM " + UserConst.TABLE_NAME +
            " WHERE " + UserConst.ID + "=? AND " + UserConst.STATUS + " != '" + status + "'";
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
        String param = builderSqlRequest(name, surname);
        return jdbcTemplate.query(GET_USER_BY_NAME_SURNAME_SQL + param, new Object[]{},
                new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User addUser(User user) {
        user.setRole(Role.USER);
        user.setStatus(Status.VERIFY);
        Number id = simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(user));
        user.setId(id.longValue());
        return user;
    }

    @Override
    public void addUserDetails(long user_id) {
        jdbcTemplate.update(ADD_ID_BY_DETAIL_SQL, user_id);
    }


    @Override
    public void updateStatus(long id, Status stat) {
        jdbcTemplate.update(UPDATE_USER_STATUS_SQL, stat.toString(), id);
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


    private String builderSqlRequest(String name, String surname) {
        String param = "";
        if ((name != null) || (surname != null)) {
            param += " WHERE ";
        }
        if (name != null) {
            param += UserDetailConst.NAME + " = '" + name + "'";
            if (surname != null) {
                param += " AND " + UserDetailConst.SURNAME + " = '" + surname + "'";
                return param;
            }
        }
        if (surname != null) {
            param += UserDetailConst.SURNAME + " = '" + surname + "'";
        }
        return param;
    }
}
