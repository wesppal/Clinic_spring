package by.overone.clinic.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "user_details")
@Data
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Pattern(regexp = "[A-Za-z]+")
    private String name;
    @Pattern(regexp = "[A-Za-z]+")
    private String surname;
    private String address;
    @Pattern(regexp = "^[+]+[\\d]{12}")
    private String phoneNumber;
}
