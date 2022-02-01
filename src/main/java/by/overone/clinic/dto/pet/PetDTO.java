package by.overone.clinic.dto.pet;

import by.overone.clinic.util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDTO {
    private long pet_id;
    private String name;
    private Integer age;
    private String type_of_pet;
    private Status status;
}
