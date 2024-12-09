package com.taxiboking.project.taxiboiking.services;

import com.taxiboking.project.taxiboiking.entities.Payment;
import com.taxiboking.project.taxiboiking.entities.Ride;
import com.taxiboking.project.taxiboiking.entities.enums.PaymentStatus;

public interface PaymentService {
    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus status);
}
