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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private static String status = Status.ACTIVE.toString();

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        criteriaQuery.from(User.class);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Optional<User> getUserById(long id) {
        return null;
    }

    @Override
    public List<User> getUserByNameSurname(String name, String surname) {
        return null;
    }

    @Override
    public User addUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public void addUserDetails(long user_id) {
    }


    @Override
    public void updateStatus(long id, Status stat) {

    }

    @Override
    public UserDetails updateUserDetails(UserDetails userDetail) {
        return null;
    }

    @Override
    public Optional<UserInfoDTO> getUserDetails(long id) {
        return null;
    }
}
