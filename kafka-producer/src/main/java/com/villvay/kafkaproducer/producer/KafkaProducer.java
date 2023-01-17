package com.villvay.kafkaproducer.producer;

import com.villvay.kafkaproducer.avro.schema.OrderHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class KafkaProducer {

    @Value("${avro.topic.name}")
    String topicName;
    @Autowired
    private KafkaTemplate<String, OrderHistory> kafkaTemplate;

    public void sendMessage(OrderHistory orderHistoryMessage) {
        log.info("Sending message {}", orderHistoryMessage);

//        Message<OrderHistoryMessage> message = MessageBuilder.withPayload(orderHistoryMessage)
//                .setHeader(KafkaHeaders.TOPIC, ORDER_HISTORY_TOPIC)
//                .build();
//
//        kafkaTemplate.send(message);

        ListenableFuture<SendResult<String, OrderHistory>> future = kafkaTemplate.send(topicName, orderHistoryMessage.getOrderId().toString(), orderHistoryMessage);
        future.addCallback(new ListenableFutureCallback<SendResult<String, OrderHistory>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("sendMessage Error", ex);
            }

            @Override
            public void onSuccess(SendResult<String, OrderHistory> result) {
                log.info("sendMessage: {}", result);
            }
        });
    }
}
