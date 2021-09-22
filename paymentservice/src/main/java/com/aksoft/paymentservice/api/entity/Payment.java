package com.aksoft.paymentservice.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="PAYMENT_TB")
public class Payment {

    @Id
    @GeneratedValue
    private int paymentId;
    private String status;
    private String transactionId;
    private int orderId;
    private double amount;
}
