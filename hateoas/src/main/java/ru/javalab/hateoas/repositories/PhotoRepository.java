package ru.javalab.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javalab.hateoas.models.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
