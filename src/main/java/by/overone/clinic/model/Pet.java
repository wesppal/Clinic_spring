package by.overone.clinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
        private long pet_id;
        private String name;
        private int age;
        private String type_of_pet;
        private String owner;
        private long user_id;
        private String status;
}
