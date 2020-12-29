package ru.javalab.rabbitmq.service;

import org.springframework.stereotype.Service;
import ru.javalab.rabbitmq.model.entity.File;
import ru.javalab.rabbitmq.repository.FileRepository;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {
    private final String pathForPassport = "C:\\projects\\javalab2020\\rabbitmq_with_spring\\src\\main\\resources\\passports\\";
    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }

    @Override
    public void save(File file) {
        fileRepository.save(file);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(pathForPassport + file.getNameWithExtension());
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File findById(String id) {
        return null;
    }
}
