package com.aksoft.paymentservice.api.service;

import com.aksoft.paymentservice.api.entity.Payment;
import com.aksoft.paymentservice.api.entity.PaymentStatus;
import com.aksoft.paymentservice.api.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private PaymentRepository repository;

    public Payment makePayment(Payment payment) {
        PaymentStatus paymentStatus = paymentProcessing();
        if (PaymentStatus.FAIL.equals(paymentStatus)) {
            logger.error("Payment Processing Failed due to some reason.");
        } else {
            logger.info("Payment Processing Successful.");
        }
        payment.setStatus(paymentStatus.name());
        payment.setTransactionId(UUID.randomUUID().toString());
        return repository.save(payment);
    }

    public PaymentStatus paymentProcessing() {
        // API call can be any payment gateway or anything
        return new Random().nextBoolean() ? PaymentStatus.SUCCESSFUL : PaymentStatus.FAIL;
    }

    public Payment findPaymentHistoryByOrderID(int orderId) {
        logger.info("Payment History for Order Id: {}", orderId);
        Payment payment = repository.findByOrderId(orderId);
        if (payment != null) {
            logger.info("Payment Id: {}", payment.getPaymentId());
        } else {
            logger.info("Order Id not found in database.");
        }
        return payment;
    }
}
