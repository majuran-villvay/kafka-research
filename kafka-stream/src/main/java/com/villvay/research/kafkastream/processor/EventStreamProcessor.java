package com.villvay.research.kafkastream.processor;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EventStreamProcessor {
  @Autowired
  StreamsBuilder streamsBuilder;
  String topic = "spring.boot.kafka.stream.input";
  String outTopic = "spring.boot.kafka.stream.output";

  @PostConstruct
  public void streamProcess() {
    final Serde<Integer> integerSerde = Serdes.Integer();
    final Serde<String> stringSerde = Serdes.String();
    final Serde<Long> longSerde = Serdes.Long();

    streamsBuilder.stream(topic, Consumed.with(stringSerde, stringSerde))
        .filter((key, value) -> value.startsWith("Message_"))
        .mapValues(value -> value.toUpperCase())
        .peek((key, value) -> System.out.println("key: " + key + " value: " + value))
        .to(outTopic, Produced.with(stringSerde, stringSerde));

  }


}
