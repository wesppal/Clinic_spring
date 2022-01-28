package by.overone.clinic.model;

import by.overone.clinic.util.Role;
import by.overone.clinic.util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(unique = true)
    private String login;
    @NotNull
    @Column(nullable = false)
    private String password;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @JoinColumn(name = "detail_id")
    @OneToOne(cascade = CascadeType.ALL)
    private UserDetails userDetails;
}
