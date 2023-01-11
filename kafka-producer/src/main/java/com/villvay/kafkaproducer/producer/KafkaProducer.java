package com.villvay.kafkaproducer.producer;

import com.villvay.kafkaproducer.model.OrderHistoryMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static com.villvay.kafkaproducer.util.Constant.ORDER_HISTORY_TOPIC;

@Service
@Slf4j
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, OrderHistoryMessage> kafkaTemplate;

    public void sendMessage(OrderHistoryMessage orderHistoryMessage) {
        log.info("Sending message {}", orderHistoryMessage);

        Message<OrderHistoryMessage> message = MessageBuilder.withPayload(orderHistoryMessage)
                .setHeader(KafkaHeaders.TOPIC, ORDER_HISTORY_TOPIC)
                .build();

        kafkaTemplate.send(message);
    }
}
