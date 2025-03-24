package com.bn.rabbitmq.messaging;

import com.bn.rabbitmq.common.CommonConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class Receiver {
    private final UserService userService;

    public Receiver(UserService userService) {
        this.userService = userService;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void receiveMessage(String message) {
        try {
//            List<User> users = CommonConverter.convertFromJson(message,new TypeReference<List<User>>(){});
//            userService.updateUser(users);
            User user = CommonConverter.convertFromJson(message, new TypeReference<User>() {});
            userService.createUser(user);
            System.out.println("Received User: " );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "anotherQueue")
    public void receiveMessage2(String message) {
        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            User user = objectMapper.readValue(message, User.class);
//            userService.updateUser(user);
//            System.out.println("Received User2: " + user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
