package by.overone.clinic.model;

import by.overone.clinic.util.Role;
import by.overone.clinic.util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String login;
    private String password;
    private String email;
    private Role role;
    private Status status;
}
