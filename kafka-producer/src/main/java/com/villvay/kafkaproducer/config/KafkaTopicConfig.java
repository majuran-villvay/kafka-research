package com.villvay.kafkaproducer.config;

import com.villvay.kafkaproducer.avro.schema.OrderHistory;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

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

    @Bean
    public ConsumerFactory<String, OrderHistory> consumerFactory(KafkaProperties kafkaProperties) {
//        kafkaProperties.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, false);
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, OrderHistory>> kafkaListenerContainerFactory(KafkaProperties kafkaProperties) {
        ConcurrentKafkaListenerContainerFactory<String, OrderHistory> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(kafkaProperties));
        return factory;
    }
}