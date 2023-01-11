package com.villvay.kafkaconsumer.consumer;

import com.villvay.kafkaconsumer.model.OrderHistoryMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.villvay.kafkaconsumer.util.Constant.GROUP_ID;
import static com.villvay.kafkaconsumer.util.Constant.ORDER_HISTORY_TOPIC;


@Service
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = ORDER_HISTORY_TOPIC, groupId = GROUP_ID)
    public void consume(OrderHistoryMessage message) {
        log.info("KafkaConsumer:consume received {}", message);
    }
}
