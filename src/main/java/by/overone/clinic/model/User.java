package by.overone.clinic.model;

import by.overone.clinic.util.Role;
import by.overone.clinic.util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
//@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(unique = true)
    private String email;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @JoinColumn(name = "detail_id")
    @OneToOne(cascade = CascadeType.ALL)
    private UserDetails userDetails;
}
