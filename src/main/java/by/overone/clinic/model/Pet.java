package by.overone.clinic.model;

import by.overone.clinic.util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
        private long pet_id;
        private String name;
        private Integer age;
        private String type_of_pet;
        private long user_id;
        private Status status;
}
