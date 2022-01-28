package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.UserDao;
import by.overone.clinic.dto.user.UserInfoDTO;
import by.overone.clinic.model.User;
import by.overone.clinic.model.UserDetails;
import by.overone.clinic.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
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
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    /**
     *
     * Надо поправить тут не написано
     *
     * @param name
     * @param surname
     * @return
     */
    @Override
    public List<User> getUserByNameSurname(String name, String surname) {
        List<User> users = new ArrayList<>();
        User user = new User(0,"HZ","HZ","HZ",Role.USER,Status.ACTIVE,new UserDetails());
        users.add(user);
        return users;
    }

    @Override
    public User addUser(User user) {
        entityManager.persist(user);
        return user;
    }


    @Override
    public void updateStatus(long id, Status stat) {

    }

    @Override
    public UserDetails updateUserDetails(UserDetails userDetail) {
        return entityManager.merge(userDetail);
    }

    @Override
    public UserDetails getUserDetails(long id) {
        return entityManager.find(UserDetails.class, id);
    }
}
