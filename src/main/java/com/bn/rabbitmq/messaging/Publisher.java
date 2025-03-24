package com.bn.rabbitmq.messaging;

import com.bn.rabbitmq.common.CommonConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Publisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessagePublisher messagePublisher;

    @PostMapping("/send")
    public String sendMessage(@RequestBody User user) {

        // Chuyển đổi đối tượng Java thành JSON
//        ObjectMapper objectMapper = new ObjectMapper();
        String message = "";
//        try {
//            message = objectMapper.writeValueAsString(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        message = CommonConverter.convertToJson(user);

        messagePublisher.sendMessageToAnotherQueue(message);
        messagePublisher.sendMessageToCompanyRatingQueue(message);
        // Gửi tin nhắn JSON tới RabbitMQ
//        rabbitTemplate.convertAndSend("companyRatingQueue", message);

        return message;
    }
}