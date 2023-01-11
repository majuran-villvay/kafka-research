package com.villvay.kafkaproducer.model;

import lombok.Data;

@Data
public class OrderHistoryMessage {
    private String orderId;
    private String message;
    private String price;

}
