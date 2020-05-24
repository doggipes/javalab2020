package ru.javalab.services;

import org.springframework.stereotype.Service;
import ru.javalab.dto.MessageDto;
import ru.javalab.models.entity.SupportTicket;

import java.util.List;

@Service
public interface SupportService {
    String identify(Long userId);

    String createUUID();

    List<MessageDto> getMessages(String pageId);

    SupportTicket save(SupportTicket supportTicket);

    SupportTicket addMessage(MessageDto messageDto);

    boolean hasToken();

    List<MessageDto> getByUniqueToken();
}
