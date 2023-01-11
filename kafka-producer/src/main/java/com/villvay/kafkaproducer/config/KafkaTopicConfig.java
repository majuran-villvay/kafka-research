package com.villvay.kafkaproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.villvay.kafkaproducer.util.Constant.ORDER_HISTORY_TOPIC;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic orderHistoryTopic() {
        return TopicBuilder.name(ORDER_HISTORY_TOPIC)
//                .partitions(10)
//                .replicas(1)
                .build();
    }
}