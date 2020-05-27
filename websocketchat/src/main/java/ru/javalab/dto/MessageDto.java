package ru.javalab.dto;

import lombok.Data;

@Data
public class MessageDto {
    private String text;
    private String from;
    private String room;
}
