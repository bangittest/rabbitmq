//package com.bn.rabbitmq.messaging;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//
//@RequiredArgsConstructor
//@Service
//public class ReviewMessageConsumer {
//    private final CompanyService companyService;
//
//    @RabbitListener(queues = "companyRatingQueue")
//    public void consumeMessage(ReviewMessage reviewMessage){
//        companyService.updateCompany(reviewMessage);
//    }
//}
