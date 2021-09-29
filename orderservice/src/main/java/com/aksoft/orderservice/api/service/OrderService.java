package com.aksoft.orderservice.api.service;

import com.aksoft.orderservice.api.entity.Order;
import com.aksoft.orderservice.api.repository.OrderRepository;
import com.aksoft.orderservice.common.Payment;
import com.aksoft.orderservice.common.TransactionRequest;
import com.aksoft.orderservice.common.TransactionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public TransactionResponse saveOrder(TransactionRequest request) {
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setAmount(order.getPrice());
        payment.setOrderId(order.getOrderId());
        //REST CALL
        logger.info("Rest Call to paymentservice sent.");
        Payment paymentResponse = template.postForObject(paymentServiceURL, payment, Payment.class);
        logger.info("Response received from paymentservice. Payment Id:  {}, Status: {}, Order ID: {}", paymentResponse.getPaymentId(), paymentResponse.getStatus(), paymentResponse.getOrderId());
        String response = "successful".equalsIgnoreCase(paymentResponse.getStatus())?"Order Placed Successfully":"Payment Failed";
        repository.save(order);
        return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(),response);
    }
}
