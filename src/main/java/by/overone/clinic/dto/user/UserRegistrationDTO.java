package by.overone.clinic.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;

@Validated
@Data
@NoArgsConstructor
public class UserRegistrationDTO {
    @Pattern(regexp = "^[\\w]{4,10}$", message = " error. The login must be from 4 to 10 Latin characters.")
    private String login;
    @Pattern(regexp = "^[\\w]{4,10}$", message = " error. The password must be from 4 to 10 Latin characters.")
    private String password;
    @Pattern(regexp = "^[\\S]+@[\\w]+\\.+[A-Za-z]+$",
            message = " error. The email can contain numbers and Latin characters. Example: example1@google1.com")
    private String email;
}