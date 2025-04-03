package com.bn.rabbitmq.messaging.test;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiver {
    // Nhận message từ queue1 (Bind với fanoutExchange1)
    @RabbitListener(queues = "queue1")
    public void receiveMessageFromQueue1(String message) {
        System.out.println("Received from queue1: " + message);
    }

    // Nhận message từ queue2 (Bind với fanoutExchange1)
    @RabbitListener(queues = "queue2")
    public void receiveMessageFromQueue2(String message) {
        System.out.println("Received from queue2: " + message);
    }

    // Nhận message từ queue3 (Bind với fanoutExchange2)
    @RabbitListener(queues = "queue3")
    public void receiveMessageFromQueue3(String message) {
        System.out.println("Received from queue3: " + message);
    }

    // Nhận message từ queue4 (Bind với fanoutExchange2)
    @RabbitListener(queues = "queue4")
    public void receiveMessageFromQueue4(String message) {
        System.out.println("Received from queue4: " + message);
    }
}
