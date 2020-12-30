package ru.javalab.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javalab.hateoas.models.Music;

public interface MusicRepository extends JpaRepository<Music, Long> {
}
