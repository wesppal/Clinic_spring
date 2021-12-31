package by.overone.clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private long id;
    private String login;
    private String email;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
}
