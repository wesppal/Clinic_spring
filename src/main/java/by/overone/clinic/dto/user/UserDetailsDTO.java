package by.overone.clinic.dto.user;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;


@Data
public class UserDetailsDTO {
    @Min(1)
    private long user_id;
    @Pattern(regexp = "[A-Za-z]+")
    private String name;
    @Pattern(regexp = "[A-Za-z]+")
    private String surname;
    private String address;
    @Pattern(regexp = "^[+]+[\\d]{12}")
    private String phoneNumber;
}
