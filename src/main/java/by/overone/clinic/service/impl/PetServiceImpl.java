package by.overone.clinic.service.impl;

import by.overone.clinic.dao.PetDao;
import by.overone.clinic.dao.UserDao;
import by.overone.clinic.dto.UserDTO;
import by.overone.clinic.dto.pet.PetAddDTO;
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

    @Override
    public List<Pet> getPets() {
        return petDao.getPets().stream().collect(Collectors.toList());
    }

    @Override
    public Pet getPetById(long id) {
        return null;
    }

    @Override
    public Pet addPet(long user_id, Pet pet) {
        return null;
    }

    @Override
    public void updatePet(long id, Pet pet) {

    }

    @Override
    public boolean deletePet(long id) {
        return false;
    }

    @Override
    public List<Pet> getPetByUserId(long user_id) {
        return null;
    }
}
