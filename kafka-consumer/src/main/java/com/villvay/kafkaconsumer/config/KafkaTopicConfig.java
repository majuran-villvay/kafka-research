package com.villvay.kafkaconsumer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import static com.villvay.kafkaconsumer.util.Constant.ORDER_HISTORY_TOPIC;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic orderHistoryTopic() {
        return TopicBuilder.name(ORDER_HISTORY_TOPIC)
//                .partitions(10)
//                .replicas(1)
                .build();
    }

    @Bean
    public StringJsonMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }
}