//package com.bn.rabbitmq.excute;
//
//import com.bn.rabbitmq.common.CommonConverter;
//import com.bn.rabbitmq.dto.TrainLocationMessage;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.util.Random;
//
//public class Schedule {
//
//    private final RabbitTemplate rabbitTemplate;
//    private final Random random = new Random();
//    private double remainingDistance = 49.55;
//    Integer a = 1;
//
//    public Schedule(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    @Scheduled(fixedRate = 1000)
//    public void sendTrainUpdate() {
//        if (remainingDistance <= 0) return;
//
//        remainingDistance = Math.max(0, remainingDistance - random.nextDouble() * 2); // Đảm bảo không bị âm
//
//        TrainLocationMessage updateJob = new TrainLocationMessage(
//                String.format("CODE-123-%d", a++),
//                1,
//                2,
//                60.0,
//                remainingDistance,
//                random.nextInt(200),
//                random.nextInt(50)
//        );
//
//        String message = CommonConverter.convertToJson(updateJob);
//        rabbitTemplate.convertAndSend("directExchange", "trainLocationUpdate", message);
//
//        System.out.printf("📍 Vị trí tàu cập nhật: %s, Khoảng cách còn lại: %.2f km%n", updateJob.getTrainCode(), remainingDistance);
//    }
//
//}
