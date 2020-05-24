package ru.javalab.repositories;

import ru.javalab.dto.MessageDto;
import ru.javalab.models.entity.SupportTicket;

import java.util.List;

public interface SupportServiceRepository extends CrudRepository<Long, SupportTicket> {
    List<SupportTicket> findByToken(String token);

    List<SupportTicket> findByUserId(Long userId);

    List<MessageDto> findByTokenUnique();
}
