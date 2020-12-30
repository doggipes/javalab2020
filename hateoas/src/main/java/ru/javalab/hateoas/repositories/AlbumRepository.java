package ru.javalab.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javalab.hateoas.models.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
