package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.PetDao;
import by.overone.clinic.dao.UserDao;
import by.overone.clinic.model.Pet;
import by.overone.clinic.util.PetConst;
import by.overone.clinic.util.Status;
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
public class PetDaoImpl implements PetDao {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    @Qualifier("pet")
    private SimpleJdbcInsert simpleJdbcInsert;

    private final UserDao userDao;
    private static String status = Status.ACTIVE.toString();

    private final static String GET_ALL_PETS_SQL = "SELECT * FROM " + PetConst.TABLE_NAME +
            " WHERE " + PetConst.STATUS + " = '" + status + "'";
    private final static String GET_PET_BY_ID_SQL = "SELECT * FROM " + PetConst.TABLE_NAME +
            " WHERE " + PetConst.ID + "=?";
    private final static String UPDATE_PET_STATUS_SQL = "UPDATE " + PetConst.TABLE_NAME + " SET " +
            PetConst.STATUS + " =(?) WHERE " + PetConst.ID + " = (?)";
    private final static String UPDATE_PET_SQL = "UPDATE " + PetConst.TABLE_NAME + " SET " +
            PetConst.NAME + "=COALESCE(?," + PetConst.NAME + "), " +
            PetConst.AGE + "=COALESCE(?," + PetConst.AGE + "), " +
            PetConst.TYPE + "=COALESCE(?," + PetConst.TYPE + "), " +
            PetConst.STATUS + "=COALESCE(?," + PetConst.STATUS + ") " +
            "WHERE " + PetConst.ID + " = ?";
    private final static String GET_PETS_BY_USER_ID_SQL = "SELECT * FROM " + PetConst.TABLE_NAME +
            " WHERE " + PetConst.USER_ID + " = ?";

    @Override
    public List<Pet> getPets() {
        return jdbcTemplate.query(GET_ALL_PETS_SQL, new BeanPropertyRowMapper<>(Pet.class));
    }

    @Override
    public Optional<Pet> getPetById(long id) {
        return jdbcTemplate.query(GET_PET_BY_ID_SQL, new Object[]{id},
                new BeanPropertyRowMapper<>(Pet.class)).stream().findAny();
    }

    @Override
    public Pet addPet(Pet pet) {
        pet.setStatus(Status.VERIFY.toString());
        Number id = simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(pet));
        pet.setPet_id(id.longValue());
        return pet;
    }

    @Override
    public void updatePet(Pet pet) {
        jdbcTemplate.update(UPDATE_PET_SQL, pet.getName(), pet.getAge(),
                pet.getType_of_pet(), pet.getStatus(), pet.getPet_id());
    }

    @Override
    public void updateStatus(long id, Enum<Status> status) {
        jdbcTemplate.update(UPDATE_PET_STATUS_SQL, status.toString(), id);
    }


    @Override
    public List<Pet> getPetByUserId(long user_id) {
        userDao.getUserById(user_id);
        return jdbcTemplate.query(GET_PETS_BY_USER_ID_SQL, new Object[]{user_id},
                new BeanPropertyRowMapper<>(Pet.class));
    }
}
