package com.bn.rabbitmq.messaging;

import org.springframework.amqp.core.*;
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

    // Tạo một Fanout Exchange (nếu chưa tồn tại)
    @Bean
    public FanoutExchange fanoutExchange1() {
        // Exchange sẽ không được tạo lại nếu đã tồn tại
        return new FanoutExchange("fanoutExchange1", true, false); // true -> durable, false -> không auto-delete
    }

    // Tạo Fanout Exchange thứ hai
    @Bean
    public FanoutExchange fanoutExchange2() {
        return new FanoutExchange("fanoutExchange2",true,false);
    }

    @Bean
    public Queue queue1() {
        // Queue 1 sẽ không được tạo lại nếu đã tồn tại
        return new Queue("queue1", true); // durable
    }

    @Bean
    public Queue queue2() {
        // Queue 2 sẽ không được tạo lại nếu đã tồn tại
        return new Queue("queue2", true); // durable
    }

    @Bean
    public Queue queue3() {
        // Queue 1 sẽ không được tạo lại nếu đã tồn tại
        return new Queue("queue3", true); // durable
    }

    @Bean
    public Queue queue4() {
        // Queue 2 sẽ không được tạo lại nếu đã tồn tại
        return new Queue("queue4", true); // durable
    }

    // Bind các queue vào fanoutExchange1
    @Bean
    public Binding binding1(FanoutExchange fanoutExchange1, Queue queue1) {
        return BindingBuilder.bind(queue1).to(fanoutExchange1);
    }

    @Bean
    public Binding binding2(FanoutExchange fanoutExchange1, Queue queue2) {
        return BindingBuilder.bind(queue2).to(fanoutExchange1);
    }

    // Bind các queue vào fanoutExchange2
    @Bean
    public Binding binding3(FanoutExchange fanoutExchange2, Queue queue3) {
        return BindingBuilder.bind(queue3).to(fanoutExchange2);
    }

    @Bean
    public Binding binding4(FanoutExchange fanoutExchange2, Queue queue4) {
        return BindingBuilder.bind(queue4).to(fanoutExchange2);
    }
}
