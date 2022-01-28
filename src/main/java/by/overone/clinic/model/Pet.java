package by.overone.clinic.model;

import by.overone.clinic.util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "pets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "pet_id")
        private long petId;
        private String name;
        private Integer age;
        @Column(name = "type_of_pet")
        private String type;
        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "user_id")
        private User user;
        private Status status;
}
