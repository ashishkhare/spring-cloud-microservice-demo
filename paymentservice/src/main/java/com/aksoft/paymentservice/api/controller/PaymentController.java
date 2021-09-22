package com.aksoft.paymentservice.api.controller;

import com.aksoft.paymentservice.api.entity.Payment;
import com.aksoft.paymentservice.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService service;

    @PostMapping("/makePayment")
    public Payment makePayment(@RequestBody Payment payment){
        return service.makePayment(payment);
    }

    @GetMapping("/{orderId}")
    public Payment findPaymentHistoryByOrderID(@PathVariable int orderId){
        return service.findPaymentHistoryByOrderID(orderId);
    }
}
