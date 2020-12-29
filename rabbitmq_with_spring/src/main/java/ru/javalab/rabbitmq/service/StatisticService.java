package ru.javalab.rabbitmq.service;

import org.springframework.stereotype.Service;

@Service
public interface StatisticService {
    void addLog(String line);
}
