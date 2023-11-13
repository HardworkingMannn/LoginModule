package com.example.loginmodule.config;

import com.example.loginmodule.constant.RabbitMQConst;
import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue emailQueue(){
        return new Queue(RabbitMQConst.emailQueueName);
    }
    @Bean
    public Queue phoneQueue(){
        return new Queue(RabbitMQConst.phoneQueueName);
    }
    @Bean
    public DirectExchange registerExchange(){
        return new DirectExchange(RabbitMQConst.registerExchange);
    }
    @Bean
    public Binding dircetBinding1(Queue emailQueue,DirectExchange registerExchange){
        return BindingBuilder.bind(emailQueue).to(registerExchange).with(RabbitMQConst.emailQueueName);
    }
    @Bean
    public Binding dircetBinding2(Queue phoneQueue,DirectExchange registerExchange){
        return BindingBuilder.bind(phoneQueue).to(registerExchange).with(RabbitMQConst.phoneQueueName);
    }
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
