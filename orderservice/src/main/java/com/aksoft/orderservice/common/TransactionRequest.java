package com.aksoft.orderservice.common;

import com.aksoft.orderservice.api.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRequest {

    private Order order;
    private Payment payment;
}
