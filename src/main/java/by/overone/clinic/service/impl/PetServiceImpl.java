package by.overone.clinic.service.impl;

import by.overone.clinic.dao.PetDao;
import by.overone.clinic.dao.UserDao;
import by.overone.clinic.exception.EntityNotFoundException;
import by.overone.clinic.exception.ExceptionCode;
import by.overone.clinic.model.Pet;
import by.overone.clinic.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetDao petDao;
    private final UserDao userDao;

    @Override
    public List<Pet> getPets() {
        return petDao.getPets().stream().collect(Collectors.toList());
    }

    @Override
    public Pet getPetById(long id) {
        Pet pet = petDao.getPetById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.NOT_EXISTING_PET.getErrorCode()));
        return pet;
    }

    @Override
    public Pet addPet(Pet pet) {
        return petDao.addPet(pet);
    }

    @Override
    public void updatePet(Pet pet) {
        petDao.getPetById(pet.getPet_id());
        petDao.updatePet(pet);
    }

    @Override
    public void removePetById(long id) {
        String status = "DELETED";
        petDao.updateStatus(id, status);
    }

    @Override
    public List<Pet> getPetByUserId(long user_id) {
        userDao.getUserById(user_id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode()));
        List<Pet> pets = petDao.getPetByUserId(user_id);
        if (pets.size() < 1) {
            throw new EntityNotFoundException(ExceptionCode.NOT_EXISTING_PETS_BY_USER.getErrorCode());
        }
            return petDao.getPetByUserId(user_id);
    }
}
