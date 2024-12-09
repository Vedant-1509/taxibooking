package com.taxiboking.project.taxiboiking.strategies;

import com.taxiboking.project.taxiboiking.entities.Payment;

public interface PaymentStrategy {
 Double PLATFORM_COMISSION=0.3;

    void processPayment(Payment payment);
}
