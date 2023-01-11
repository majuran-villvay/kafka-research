package com.villvay.kafkaconsumer.model;

import lombok.Data;

@Data
public class OrderHistoryMessage {
    private String orderId;
    private String message;
    private String price;

}
