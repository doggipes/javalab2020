package ru.javalab.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.javalab.models.enums.Role;
import ru.javalab.models.enums.State;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "person")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user")
    private Long id;

    @Column(name="name_of_user")
    private String name;
    @Column(name="email_of_user")
    private String email;
    @Column(name="password_of_user")
    private String password;

    @Column(name="state_of_user")
    @Enumerated(value = EnumType.STRING)
    private State state;

    @Column(name="role_of_user")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Transient
    private List<Post> posts;
}
