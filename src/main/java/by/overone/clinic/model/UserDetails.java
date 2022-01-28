package by.overone.clinic.model;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class UserDetails {
    private long user_id;
    @Pattern(regexp = "[A-Za-z]+")
    private String name;
    @Pattern(regexp = "[A-Za-z]+")
    private String surname;
    private String address;
    @Pattern(regexp = "^[+]+[\\d]{12}")
    private String phoneNumber;
}
