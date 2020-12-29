package ru.javalab.rabbitmq.service;

import ru.javalab.rabbitmq.model.entity.File;

public interface FileService {
    void save(File file);

    File findById(String id);
}
