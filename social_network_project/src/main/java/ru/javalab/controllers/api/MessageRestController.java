package ru.javalab.controllers.api;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.javalab.dto.MessageDto;
import ru.javalab.security.details.UserDetailsImpl;
import ru.javalab.services.SupportService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MessageRestController {
    @Autowired
    SupportService supportService;

    private static final Map<Long, List<MessageDto>> messages = new HashMap<>();

    @PostMapping("/messages")
    public ResponseEntity<Object> receiveMessage(@RequestBody MessageDto message, Authentication authentication) {
        if(message.getText().trim().isEmpty())
            return ResponseEntity.ok().build();

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getUser().getId();

        message.setUserId(userId);
        message = MessageDto.from(supportService.addMessage(message));

            for (List<MessageDto> pageMessages : messages.values()) {
                synchronized (pageMessages) {
                    pageMessages.add(message);
                    pageMessages.notifyAll();
                }
            }

        return ResponseEntity.ok().build();
    }

    @SneakyThrows
    @GetMapping("/messages")
    public ResponseEntity<List<MessageDto>> getMessagesForPage(@RequestParam("pageId") String pageId, Authentication authentication) {
        System.out.println(pageId);
        System.out.println(messages.size());

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getUser().getId();

        if (!messages.containsKey(userId)) {
            messages.put(userId, new ArrayList<>());
        }

        synchronized (messages.get(userId)) {
            if (messages.get(userId).isEmpty()) {
                messages.get(userId).wait();
            }
        }

        List<MessageDto> response = new ArrayList<>(messages.get(userId));
        messages.get(userId).clear();


        return ResponseEntity.ok(response);
    }
}
