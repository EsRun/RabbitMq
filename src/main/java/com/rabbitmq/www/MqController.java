package com.rabbitmq.www;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqController {
	private static final String EXCHANGE_NAME = "sample.exchange";
	
	private RabbitTemplate rabbitTemplate; 
	
	public MqController(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	@GetMapping("sample/queue")
	public String publishMsg() {
		rabbitTemplate.convertAndSend(EXCHANGE_NAME, "sample.routing.#", "OK");
		return "Message Publish";
	}
}
