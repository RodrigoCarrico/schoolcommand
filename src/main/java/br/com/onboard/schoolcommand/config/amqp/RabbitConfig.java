package br.com.onboard.schoolcommand.config.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

	@Value("${queue.school.name}")
	private String schoolQueue;
	
	@Value("${exchange.school.name}")
	private String exchangeQueue;

	@Bean
	public Exchange fanoutExchange() {
		return ExchangeBuilder.fanoutExchange(exchangeQueue).durable(true).build();
	}

	@Bean
	public Binding schoolQueueBinding() {
		return BindingBuilder.bind(jsonQueue()).to(fanoutExchange()).with("").noargs();
	}

	@Bean
	public Queue jsonQueue() {
		return QueueBuilder.durable(schoolQueue).build();
	}

	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter("*");
	}
}
