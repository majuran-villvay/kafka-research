package com.villvay.kafkaproducer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.villvay.kafkaproducer.util.Constant.ORDER_HISTORY_TOPIC;

@Service
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = ORDER_HISTORY_TOPIC, groupId = "group_id")
    public void consume(String message) {
        log.info("KafkaConsumer:consume received {}", message);
    }
}
