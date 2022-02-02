package by.overone.clinic.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @Min(1)
    private long id;
    private String login;
    private String email;
}
