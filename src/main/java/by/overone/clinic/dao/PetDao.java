package by.overone.clinic.dao;

import by.overone.clinic.model.Pet;

import java.util.List;

public interface PetDao {
    List<Pet> getPets();

    Pet getPetById(long id);

    Pet addPet(long user_id, Pet pet);

    Pet updatePet(long id, Pet pet);

    void deletePet(long id);

    List<Pet> getPetByUserId(long user_id);
}
