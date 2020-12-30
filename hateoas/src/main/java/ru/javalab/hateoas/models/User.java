package ru.javalab.hateoas.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String password;
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Album> albums;

    @OneToMany(mappedBy = "user")
    private List<Music> musics;

    @OneToMany(mappedBy = "user")
    private List<Message> messages;


    @ManyToMany
    List<Dialog> dialogs;
}
