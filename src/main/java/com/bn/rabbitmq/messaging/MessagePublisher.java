package com.bn.rabbitmq.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagePublisher {

    private final RabbitTemplate rabbitTemplate;

    public MessagePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessageToCompanyRatingQueue(String message) {
        rabbitTemplate.convertAndSend("directExchange", "companyRatingRoutingKey", message);
    }

    public void sendMessageToAnotherQueue(String message) {
        rabbitTemplate.convertAndSend("directExchange", "anotherRoutingKey", message);
    }
}