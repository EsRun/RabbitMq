package com.rabbitmq.www;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;

public class MqConfig {
	private static final String EXCHANGE_NAME = "sample.exchange";
	private static final String QUEUE_NAME = "sample.queue";
	private static final String ROUTING_KEY = "sample.routing.#";

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}
	
	@Bean
	Queue queue() {
		return new Queue(QUEUE_NAME);
	}
	/*
	 * exchange와 ROUTING_KEY의 패턴이 일치하는 queue에 메세지 전달
	 */
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}
	
	@Bean
	RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());// rabbitMq 메세지 형식으로 변환
		return rabbitTemplate;
	}
	
}
