package ru.javalab.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javalab.hateoas.models.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
