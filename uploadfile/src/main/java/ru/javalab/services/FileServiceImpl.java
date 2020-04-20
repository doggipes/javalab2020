package ru.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.javalab.models.FileModel;
import ru.javalab.repositories.FileRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    FileRepository fileRepository;

    @Override
    public void upload(MultipartFile file, String filename) throws IOException {
        InputStream inputStream = file.getInputStream();
        String path = "C:\\Users\\Джалил\\Downloads\\" + filename + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        File newFile = new File(path);

        FileOutputStream fileOutputStream = new FileOutputStream(newFile);
        byte data[] = new byte[1024];
        int byteCount;

        while ((byteCount = inputStream.read(data, 0, 1024)) != -1) {
            fileOutputStream.write(data, 0, byteCount);
        }

        fileOutputStream.flush();
        fileOutputStream.close();
        inputStream.close();

        FileModel fileModel = FileModel.builder()
                                        .name(filename)
                                        .size(file.getSize())
                                        .type(file.getContentType())
                                        .path(path)
                                        .build();
        fileRepository.save(fileModel);
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
