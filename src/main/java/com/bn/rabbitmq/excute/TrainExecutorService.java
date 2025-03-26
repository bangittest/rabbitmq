package com.bn.rabbitmq.excute;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TrainExecutorService {
    private final ConcurrentHashMap<String, ExecutorService> trainExecutors = new ConcurrentHashMap<>();

    // Lấy ExecutorService của tàu, nếu chưa có thì tạo mới
    public ExecutorService getExecutor(String trainCode) {
        return trainExecutors.computeIfAbsent(trainCode, key -> Executors.newSingleThreadExecutor());
    }

    // Đóng ExecutorService khi không cần nữa (nếu cần)
    public void shutdownExecutor(String trainCode) {
        ExecutorService executor = trainExecutors.remove(trainCode);
        if (executor != null) {
            executor.shutdown();
        }
    }
}