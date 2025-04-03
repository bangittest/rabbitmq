package com.bn.rabbitmq.messaging.test;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    // Gửi message tới fanoutExchange1
    public void sendMessageToExchange1(String message) {
        amqpTemplate.convertAndSend("fanoutExchange1", "", message);
    }

    // Gửi message tới fanoutExchange2
    public void sendMessageToExchange2(String message) {
        amqpTemplate.convertAndSend("fanoutExchange2", "", message);
    }
}
