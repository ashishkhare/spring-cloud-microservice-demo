package com.aksoft.paymentservice.api.service;

import com.aksoft.paymentservice.api.entity.Payment;
import com.aksoft.paymentservice.api.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository repository;

    public Payment makePayment(Payment payment){
        payment.setStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        return repository.save(payment);
    }
    public String paymentProcessing(){
        // API call can be any payment gateway or anything
        return new Random().nextBoolean()?"successful":"fail";
    }

    public Payment findPaymentHistoryByOrderID(int orderId) {
        return repository.findByOrderId(orderId);
    }
}
