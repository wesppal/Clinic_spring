package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.PetDao;
import by.overone.clinic.dao.UserDao;
import by.overone.clinic.dto.UserInfoDTO;
import by.overone.clinic.model.Pet;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PetDaoImpl implements PetDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;

    private final static String GET_ALL_PETS_SQL = "SELECT * FROM pets where status = 'ACTIVE'";
    private final static String GET_PET_BY_ID_SQL = "SELECT * FROM pets WHERE pet_id=?";
    private final static String ADD_NEW_PET_SQL = "INSERT INTO pets VALUE (0,?,?,?,?,?,?)";
    private final static String UPDATE_PET_STATUS_SQL = "UPDATE pets SET status =(?) WHERE pet_id=(?)";
    private final static String UPDATE_PET_SQL = "UPDATE pets SET " +
            "name=COALESCE(?,name), " +
            "age=COALESCE(?,age), " +
            "type_of_pet=COALESCE(?,type_of_pet), " +
            "owner=COALESCE(?,owner), " +
            "user_id=COALESCE(?,user_id), " +
            "status=COALESCE(?,status) " +
            "WHERE pet_id = ?";
    private final static String GET_PETS_BY_USER_ID_SQL = "SELECT * FROM pets WHERE user_id = ?";

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
        userDao.getUserById(pet.getUser_id());
        pet.setStatus("VERIFY");
        UserInfoDTO user = userDao.getUserDetails(pet.getUser_id()).orElseThrow();
        pet.setOwner(user.getName());
        jdbcTemplate.update(ADD_NEW_PET_SQL, pet.getName(), pet.getAge(), pet.getType_of_pet(),
                pet.getOwner(), pet.getUser_id(), pet.getStatus(), pet.getPet_id());
        return pet;
    }

    @Override
    public Pet updatePet(Pet pet) {
        Pet temp = getPetById(pet.getPet_id()).orElseThrow();
        if (pet.getUser_id() == 0) {
            pet.setUser_id(temp.getUser_id());
        }
        if (pet.getAge() == 0) {
            pet.setAge(temp.getAge());
        }
        jdbcTemplate.update(UPDATE_PET_SQL, pet.getName(), pet.getAge(),
                pet.getType_of_pet(), pet.getOwner(), pet.getUser_id(), pet.getStatus(), pet.getPet_id());
        return pet;
    }

    @Override
    public void updateStatus(long id, String status) {
        jdbcTemplate.update(UPDATE_PET_STATUS_SQL, status, id);
    }


    @Override
    public List<Pet> getPetByUserId(long user_id) {
        userDao.getUserById(user_id);
        return jdbcTemplate.query(GET_PETS_BY_USER_ID_SQL, new Object[]{user_id},
                new BeanPropertyRowMapper<>(Pet.class));
    }
}
