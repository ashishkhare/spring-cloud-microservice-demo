package com.aksoft.orderservice.api.service;

import com.aksoft.orderservice.api.entity.Order;
import com.aksoft.orderservice.api.repository.OrderRepository;
import com.aksoft.orderservice.common.Payment;
import com.aksoft.orderservice.common.TransactionRequest;
import com.aksoft.orderservice.common.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    @Lazy
    private RestTemplate template;

    @Value("${microservice.paymentservice.endpoints.endpoint.uri}")
    private String paymentServiceURL;

    public TransactionResponse saveOrder(TransactionRequest request) {
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setAmount(order.getPrice());
        payment.setOrderId(order.getOrderId());
        //REST CALL
        Payment paymentResponse = template.postForObject(paymentServiceURL, payment, Payment.class);
        String response = "successful".equalsIgnoreCase(paymentResponse.getStatus())?"Order Placed Successfully":"Payment Failed";
        repository.save(order);
        return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(),response);
    }
}
