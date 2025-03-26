package com.bn.rabbitmq.excute;

import com.bn.rabbitmq.dto.TrainLocationMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
public class TrainEventConsumer {
    private final TrainProcessingService trainProcessingService;
    private final TrainExecutorService trainExecutorService;
    private final ObjectMapper objectMapper;

    public TrainEventConsumer(TrainProcessingService trainProcessingService, TrainExecutorService trainExecutorService, ObjectMapper objectMapper) {
        this.trainProcessingService = trainProcessingService;
        this.trainExecutorService = trainExecutorService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void receiveMessage(String message) {
        try {
            TrainLocationMessage event = objectMapper.readValue(message, TrainLocationMessage.class);
            System.out.println("📩 Nhận dữ liệu từ Queue: " + event);

            // Lấy ExecutorService cho tàu và xử lý trên luồng riêng
            ExecutorService executor = trainExecutorService.getExecutor(event.getTrainCode());
            executor.submit(() -> trainProcessingService.processTrain(event));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
