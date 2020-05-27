package ru.javalab.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SocketDto {
    private String from;
    private String room;
}
