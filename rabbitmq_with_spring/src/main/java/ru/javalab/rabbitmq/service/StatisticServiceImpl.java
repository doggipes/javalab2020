package ru.javalab.rabbitmq.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Override
    public void addLog(String line) {
        String filePath = "C:\\projects\\javalab2020\\rabbitmq_with_spring\\src\\main\\resources\\results\\statistic.txt";

        try {
            Files.write(Paths.get(filePath), line.getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
