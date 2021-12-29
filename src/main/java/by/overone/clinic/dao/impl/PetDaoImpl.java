package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.PetDao;
import by.overone.clinic.model.Pet;
import by.overone.clinic.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Repository
@RequiredArgsConstructor
public class PetDaoImpl implements PetDao {

    private final JdbcTemplate jdbcTemplate;

    private final static String GET_ALL_PETS_SQL = "SELECT * FROM pets where status = 'ACTIVE'";
    private final static String GET_PET_BY_ID_SQL = "SELECT * FROM pets WHERE pet_id=?";
    private final static String ADD_NEW_PET_SQL = "INSERT INTO pets VALUE (0,?,?,?,?,?,?)";
    private final static String UPDATE_PET_STATUS_SQL = "UPDATE pets SET status =(?) WHERE pet_id=(?)";
    private final static String UPDATE_PET_SQL = "UPDATE pets SET";
    private final static String GET_PETS_BY_USER_ID_SQL = "SELECT * FROM pets WHERE user_id=(?)";

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
    public Pet addPet(long user_id, Pet pet) {
        return null;
    }

    @Override
    public Pet updatePet(long id, Pet pet) {
        return null;
    }

    @Override
    public void deletePet(long id) {

    }

    @Override
    public List<Pet> getPetByUserId(long user_id) {
        return null;
    }
}
