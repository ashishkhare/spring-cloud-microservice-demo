package com.aksoft.orderservice.api.controller;

import com.aksoft.orderservice.api.service.OrderService;
import com.aksoft.orderservice.common.TransactionRequest;
import com.aksoft.orderservice.common.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService service;

    @PostMapping("/placeOrder")
    public TransactionResponse placeOrder(@RequestBody TransactionRequest request){
        return service.saveOrder(request);
    }
}
