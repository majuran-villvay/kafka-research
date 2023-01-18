package com.villvay.kafkaproducer.consumer;

import com.villvay.kafkaproducer.avro.schema.OrderHistory;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = "${avro.topic.name}", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, OrderHistory> consumerRecord) {
        String key = consumerRecord.key();
        OrderHistory orderHistory = consumerRecord.value();
        log.info("KafkaConsumer:consume received key: {} and record: {}", key, orderHistory);
    }
}
