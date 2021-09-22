package com.aksoft.orderservice.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Payment {
    private int paymentId;
    private String status;
    private String transactionId;
    private int orderId;
    private double amount;
}
