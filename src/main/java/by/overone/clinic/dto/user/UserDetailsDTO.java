package by.overone.clinic.dto.user;

import lombok.Data;

import javax.validation.constraints.Pattern;


@Data
public class UserDetailsDTO {
    @Pattern(regexp = "[A-Za-z]+", message = " error. The name must consist only of Latin characters.")
    private String name;
    @Pattern(regexp = "[A-Za-z]+", message = " error.  The surname must consist only of Latin characters.")
    private String surname;
    private String address;
    @Pattern(regexp = "^[+]+[\\d]{12}", message = " error. The number must consist of digits and start with [+]. " +
            "Example: +375291231213.")
    private String phoneNumber;
}
