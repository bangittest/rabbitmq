package com.bn.rabbitmq.messaging.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageSender messageSender;

    @GetMapping("/sendToExchange1")
    public String sendMessageToExchange1() {
        messageSender.sendMessageToExchange1("Message to Exchange 1");
        return "Message sent to Exchange 1!";
    }

    @GetMapping("/sendToExchange2")
    public String sendMessageToExchange2() {
        messageSender.sendMessageToExchange2("Message to Exchange 2");
        return "Message sent to Exchange 2!";
    }
}
