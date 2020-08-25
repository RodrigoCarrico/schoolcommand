package br.com.onboard.schoolcommand.utils;

import java.io.Serializable;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SchoolQueueSender<T> implements Serializable {

	private static final long serialVersionUID = -5665076455389226416L;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private Queue queue;
	
	@Autowired Exchange exchange;

	public void send(T message, String table) {
		System.out.println("Preparando envio de mensagem Exchange: " + this.exchange.getName() + " Queue: " + this.queue.getName());
		rabbitTemplate.convertAndSend(this.exchange.getName(), this.queue.getName(), message, msg -> {
		        msg.getMessageProperties().setHeader("tableId", table);
		        return msg;
		 });
		System.out.println("Enviada a menssagem ao Rabbit ");

	}
}