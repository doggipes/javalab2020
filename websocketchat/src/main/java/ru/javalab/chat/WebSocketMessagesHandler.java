package ru.javalab.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.javalab.dto.MessageDto;
import ru.javalab.dto.SocketDto;

import java.util.HashMap;
import java.util.Map;

@Component
@EnableWebSocket
public class WebSocketMessagesHandler extends TextWebSocketHandler {
    private static final Map<SocketDto, WebSocketSession> sessions = new HashMap<>();

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String messageText = (String) message.getPayload();
        MessageDto messageFromJson = objectMapper.readValue(messageText, MessageDto.class);
        SocketDto socketDto = SocketDto.builder()
                                        .from(messageFromJson.getFrom())
                                        .room(messageFromJson.getRoom())
                                        .build();

        if (!sessions.containsKey(socketDto)) {
            sessions.put(socketDto, session);
        }

        for (Map.Entry<SocketDto, WebSocketSession> maps : sessions.entrySet()) {
            if(maps.getKey().getRoom().equals(messageFromJson.getRoom()))
                maps.getValue().sendMessage(message);
        }
    }
}
