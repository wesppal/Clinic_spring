package by.overone.clinic.dao;

import by.overone.clinic.model.Pet;

import java.util.List;
import java.util.Optional;

public interface PetDao {
    List<Pet> getPets();

    Optional<Pet> getPetById(long id);

    Pet addPet(long user_id, Pet pet);

    Pet updatePet(long id, Pet pet);

    void deletePet(long id);

    List<Pet> getPetByUserId(long user_id);
}
