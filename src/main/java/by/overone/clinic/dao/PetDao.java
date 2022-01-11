package by.overone.clinic.dao;

import by.overone.clinic.model.Pet;

import java.util.List;
import java.util.Optional;

public interface PetDao {
    List<Pet> getPets();

    Optional<Pet> getPetById(long id);

    Pet addPet(Pet pet);

    Pet updatePet(Pet pet);

    void updateStatus(long id, String status);

//    void deletePet(long id);

    List<Pet> getPetByUserId(long user_id);
}
