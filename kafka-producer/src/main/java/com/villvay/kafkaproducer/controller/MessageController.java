package com.villvay.kafkaproducer.controller;

import com.villvay.kafkaproducer.avro.schema.OrderHistory;
import com.villvay.kafkaproducer.model.OrderHistoryMessage;
import com.villvay.kafkaproducer.producer.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
@Slf4j
public class MessageController {
    @Autowired
    private KafkaProducer kafkaProducer;

    //localhost:9000/api/v1/kafka/publish
    @PostMapping("/publish")
    public ResponseEntity<OrderHistoryMessage> publish(@RequestBody OrderHistoryMessage orderHistoryMessage) {
        log.info("MessageController:publish: {}", orderHistoryMessage);
        OrderHistory orderHistory = OrderHistory.newBuilder()
                .setMessage(orderHistoryMessage.getMessage())
                .setOrderId(orderHistoryMessage.getOrderId())
                .setPrice(orderHistoryMessage.getPrice())
                .build();
//        orderHistory.setMessage(orderHistoryMessage.getMessage());
//        orderHistory.setOrderId(orderHistoryMessage.getOrderId());
//        orderHistory.setPrice(orderHistoryMessage.getPrice());


        kafkaProducer.sendMessage(orderHistory);
        return ResponseEntity.ok().body(orderHistoryMessage);
    }

}
