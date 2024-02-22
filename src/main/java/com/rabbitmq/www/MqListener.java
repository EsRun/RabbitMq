package com.rabbitmq.www;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MqListener {
	private static final Logger log = LoggerFactory.getLogger(MqListener.class);
	
	/*
	 * queues : 메세지를 가져올 큐 이름(sample.queue) 설정
	 * MqConfig(상세 설정 클래스) 필요
	 */
	@RabbitListener(queues = "sample.queue")
	public void receiveMessage(final Message message) {
		log.info(message.toString());
	}
	
	
	/*
	 * bindings = @QueueBinding : 바인딩할 정보를 배열로 받음
	 * value : @Queue(value = 메세지를 가져올 큐 이름(sample.queue) 설정, durable = 메세지 소실 시 기존 메세지 유지 설정)
	 * exchange : @Exchange(name = 메세지를 교환할 exchange 이름(sample.exchange) 설정, type = fanout(라우팅키나 패턴과 관계없이 바인딩된 모든 대기열에 메세지 전송), default 값 direct)
	 * key : 라우킹 키 설정
	 * bindings 사용 시 MqConfig(상세 설정 클래스) 불필요, 여기에서 MqConfig 내용을 모두 입력한다.
	 * 
	 
	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(value = "sample.queue", durable = "true"),
			exchange = @Exchange(name = "sample.exchange", type = "direct"),
			key = "sample.routing.#"))
	public void receiveMessage(final Message message) {
		log.info(message.toString());
	}
	*/
}
