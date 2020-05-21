package ru.javalab.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_post")
    private Long id;

    @Column(name="text_of_post")
    private String text;
    @Column(name="image_of_post")
    private String image;

    @Column(name = "user_id_of_post")
    private Long userId;

    @Transient
    private User user;
}
