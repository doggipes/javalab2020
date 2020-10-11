package ru.javalab.rabbitmq.model.enums;

public enum Certificate {
    AKADEM("C:\\projects\\javalab2020\\rabbitmq\\src\\main\\java\\ru\\javalab\\rabbitmq\\example\\aka.pdf"),
    UVOLNENIE("C:\\projects\\javalab2020\\rabbitmq\\src\\main\\java\\ru\\javalab\\rabbitmq\\example\\uvol.pdf"),
    OTCHISLENIE("C:\\projects\\javalab2020\\rabbitmq\\src\\main\\java\\ru\\javalab\\rabbitmq\\example\\otch.pdf");

    private final String url;

    Certificate(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
