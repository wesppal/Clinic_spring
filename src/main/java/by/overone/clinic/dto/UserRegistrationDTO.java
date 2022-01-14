package by.overone.clinic.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
public class UserRegistrationDTO {
    @Pattern(regexp = "^[\\w]{4,10}$")
    private String login;
    @Pattern(regexp = "^[\\w]{4,10}$")
    private String password;
    @Pattern(regexp = "^[\\S]+@[\\w]+\\.+[A-Za-z]+$")
    private String email;
}