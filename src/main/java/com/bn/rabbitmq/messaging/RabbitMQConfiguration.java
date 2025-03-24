package com.bn.rabbitmq.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfiguration {

    // Tạo Queue
    @Bean
    public Queue companyRatingQueue(){
        return new Queue("companyRatingQueue");
    }

    @Bean
    public Queue anotherQueue(){
        return new Queue("anotherQueue");  // Tạo thêm một Queue khác
    }

    // Tạo Exchange
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange"); // Tạo một direct exchange
    }

    @Bean
    public Binding bindingCompanyRatingQueue() {
        return BindingBuilder.bind(companyRatingQueue()).to(directExchange()).with("companyRatingRoutingKey"); // Đính kèm routing key cho companyRatingQueue
    }

    @Bean
    public Binding bindingAnotherQueue() {
        return BindingBuilder.bind(anotherQueue()).to(directExchange()).with("anotherRoutingKey"); // Đính kèm routing key cho anotherQueue
    }

    // Cấu hình Message Converter (chuyển đổi message sang JSON)
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    // Cấu hình RabbitTemplate
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate =
                new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
