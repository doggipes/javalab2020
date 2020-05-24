package ru.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.javalab.models.entity.SupportTicket;
import ru.javalab.models.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MessageDto {
    private String pageId;
    private String text;
    private Long userId;
    private User user;

    public static MessageDto from(SupportTicket supportTicket) {
        return MessageDto.builder()
                .text(supportTicket.getText())
                .pageId(supportTicket.getToken())
                .user(supportTicket.getUser())
                .userId(supportTicket.getUserId())
                .build();
    }

    public static List<MessageDto> from(List<SupportTicket> posts) {
        return posts.stream()
                .map(MessageDto::from)
                .collect(Collectors.toList());
    }
}
