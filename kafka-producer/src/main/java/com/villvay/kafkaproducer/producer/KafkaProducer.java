package com.villvay.kafkaproducer.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.villvay.kafkaproducer.util.Constant.ORDER_HISTORY_TOPIC;

@Service
@Slf4j
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        log.info("Sending message {}", message);
        kafkaTemplate.send(ORDER_HISTORY_TOPIC, message);
    }
}
