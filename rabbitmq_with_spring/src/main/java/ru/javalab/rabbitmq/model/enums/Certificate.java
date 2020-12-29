package ru.javalab.rabbitmq.model.enums;

public enum Certificate {
    AKADEM("C:\\projects\\javalab2020\\rabbitmq_with_spring\\src\\main\\resources\\example\\aka.pdf"),
    UVOLNENIE("C:\\projects\\javalab2020\\rabbitmq_with_spring\\src\\main\\resources\\example\\uvol.pdf"),
    OTCHISLENIE("C:\\projects\\javalab2020\\rabbitmq_with_spring\\src\\main\\resources\\example\\otch.pdf");

    private final String url;

    Certificate(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
