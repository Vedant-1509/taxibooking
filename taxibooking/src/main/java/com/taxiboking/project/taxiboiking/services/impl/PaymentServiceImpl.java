package com.taxiboking.project.taxiboiking.services.impl;

import com.taxiboking.project.taxiboiking.entities.Payment;
import com.taxiboking.project.taxiboiking.entities.Ride;
import com.taxiboking.project.taxiboiking.entities.enums.PaymentStatus;
import com.taxiboking.project.taxiboiking.exceptions.ResourceNotFoundException;
import com.taxiboking.project.taxiboiking.repositories.PaymentRepository;
import com.taxiboking.project.taxiboiking.services.PaymentService;
import com.taxiboking.project.taxiboiking.strategies.PaymentStrategyManger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManger paymentStrategyManger;


    @Override
    public void processPayment(Ride ride) {
        Payment payment = paymentRepository.findByRide(ride)
                .orElseThrow(()->new ResourceNotFoundException("Payment not foumd for ride "+ride.getId()));
        paymentStrategyManger.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);
    }

    @Override
    public Payment createNewPayment(Ride ride) {
        Payment payment = Payment.builder()
                .ride(ride)
                .paymentMethod(ride.getPaymentMethod())
                .amount(ride.getFare())
                .paymentStatus(PaymentStatus.PENDING)
                .build();
        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus status) {
        payment.setPaymentStatus(status);
        paymentRepository.save(payment);
    }
}
