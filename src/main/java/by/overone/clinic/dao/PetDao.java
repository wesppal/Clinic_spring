package by.overone.clinic.dao;

import by.overone.clinic.model.Pet;
import by.overone.clinic.util.Status;

import java.util.List;
import java.util.Optional;

public interface PetDao {
    List<Pet> getPets();

    Optional<Pet> getPetById(long id);

    Pet addPet(Pet pet);

    void updatePet(Pet pet);

    void updateStatus(long id, Status status);

    List<Pet> getPetByUserId(long user_id);
}
