package by.overone.clinic.service;

import by.overone.clinic.model.Pet;

import java.util.List;

public interface PetService {
    List<Pet> getPets();

    Pet getPetById(long id);

    Pet addPet(Pet pet);

    void updatePet(long id, Pet pet);

    void updateStatus(long id, String status);

    void deletePet(long id);

    List<Pet> getPetByUserId(long user_id);
}
