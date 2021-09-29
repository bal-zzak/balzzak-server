package com.balzzak.common.template;

import com.balzzak.common.message.MessageBase;
import com.balzzak.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReceiveAndReplyCallback;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Slf4j
public class BalzzakRabbitTemplate extends RabbitTemplate {

    public <T> T SendMessageAndReceive(MessageBase message) throws AmqpException {

        Object obj = this.convertSendAndReceive(message.getExchangeName(), message.getKey(), message);
        if(obj == null) {
            //throw new Exception();
        }
        obj = JsonUtil.fromJson(obj.toString());
        return (T) obj;
    }

    public <T> void SendMessageAndReceiveAck(MessageBase message) throws AmqpException {
        Object obj = super.convertSendAndReceive(message.getExchangeName(), message.getKey(), message);
        if(obj instanceof Exception) {
            //throw new Exception();
            // 예외 처리 부분 추후 추가 (Ack 인지 Nak 인지 )
        }
    }

    @Override
    protected Message doSendAndReceive(String exchange, String routingKey, Message message, CorrelationData correlationData) {
        return super.doSendAndReceive(exchange, routingKey, message, correlationData);
    }

    @Override
    public <R, S> boolean receiveAndReply(String queueName, ReceiveAndReplyCallback<R, S> callback) throws AmqpException {
        return super.receiveAndReply(queueName, callback);
    }

    @Override
    public Object convertSendAndReceive(String exchange, String routingKey, Object message, CorrelationData correlationData) throws AmqpException {
        return super.convertSendAndReceive(exchange, routingKey, message, correlationData);
    }

    @Override
    public Message sendAndReceive(String exchange, String routingKey, Message message) throws AmqpException {
        return super.sendAndReceive(exchange, routingKey, message);
    }
}
