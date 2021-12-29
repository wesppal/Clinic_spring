package by.overone.clinic.service;

import by.overone.clinic.dto.pet.PetAddDTO;
import by.overone.clinic.model.Pet;

import java.util.List;

public interface PetService {
    List<Pet> getPets();

    Pet getPetById(long id);

    Pet addPet(long user_id, Pet pet);

    void updatePet(long id, Pet pet);

    boolean deletePet(long id);

    List<Pet> getPetByUserId(long user_id);
}
