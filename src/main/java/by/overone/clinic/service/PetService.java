package by.overone.clinic.service;

import by.overone.clinic.model.Pet;

import java.util.List;

public interface PetService {
    List<Pet> getPets();

    Pet getPetById(long id);

    Pet addPet(Pet pet);

    Pet updatePet(Pet pet);

    Pet removePetById(long id);

    List<Pet> getPetByUserId(long user_id);
}
