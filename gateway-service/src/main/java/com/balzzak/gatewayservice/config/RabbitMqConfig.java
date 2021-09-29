package com.balzzak.gatewayservice.config;


import com.balzzak.common.template.BalzzakRabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

  private final ConnectionFactory connectionFactory;

  public RabbitMqConfig(ConnectionFactory connectionFactory) {
    this.connectionFactory = connectionFactory;
  }

  @Bean
  public BalzzakRabbitTemplate amqpTemplate() {
    BalzzakRabbitTemplate rabbitTemplate = new BalzzakRabbitTemplate();
    rabbitTemplate.setConnectionFactory(connectionFactory);
    rabbitTemplate.setMandatory(true);
    rabbitTemplate.setChannelTransacted(false);   // convertSendAndReceive 사용시 에러난다 // 나중에 확인
    rabbitTemplate.setReplyTimeout(60000);
    rabbitTemplate.setMessageConverter(queueMessageConverter());
    return rabbitTemplate;
  }

  private Jackson2JsonMessageConverter queueMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }


}
