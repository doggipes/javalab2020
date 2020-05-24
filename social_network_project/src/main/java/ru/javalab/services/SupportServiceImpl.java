package ru.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javalab.dto.MessageDto;
import ru.javalab.models.entity.SupportTicket;
import ru.javalab.repositories.SupportServiceRepository;
import ru.javalab.repositories.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class SupportServiceImpl implements SupportService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    SupportServiceRepository supportServiceRepository;

    private boolean hasToken = false;

    @Override
    public String identify(Long userId) {
        List<SupportTicket> current = supportServiceRepository.findByUserId(userId);

        if(current.isEmpty()){
            String uuid = createUUID();
            return uuid;
        }
        else{
            hasToken = true;
            return current.get(current.size() - 1).getToken();
        }

    }

    @Override
    public String createUUID() {
        return UUID.randomUUID().toString();
    }

    @Override
    public SupportTicket addMessage(MessageDto messageDto) {
        return save(SupportTicket.builder()
                            .text(messageDto.getText())
                            .userId(messageDto.getUserId())
                            .token(messageDto.getPageId())
                            .build());
    }

    @Override
    public boolean hasToken() {
        return hasToken;
    }

    @Override
    public List<MessageDto> getByUniqueToken() {
        return supportServiceRepository.findByTokenUnique();
    }

    @Override
    public List<MessageDto> getMessages(String pageId) {
        List<SupportTicket> supportTickets = supportServiceRepository.findByToken(pageId);

        return MessageDto.from(supportTickets);
    }

    @Override
    public SupportTicket save(SupportTicket supportTicket) {
        return supportServiceRepository.save(supportTicket);
    }


}
