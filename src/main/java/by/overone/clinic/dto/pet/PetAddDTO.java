package by.overone.clinic.dto.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetAddDTO {
    private String name;
    private String owner;
    private long user_id;
}
