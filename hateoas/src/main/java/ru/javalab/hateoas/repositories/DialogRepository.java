package ru.javalab.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javalab.hateoas.models.Dialog;

public interface DialogRepository extends JpaRepository<Dialog, Long> {
}
