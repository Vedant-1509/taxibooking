package com.taxiboking.project.taxiboiking.strategies.impl;

import com.taxiboking.project.taxiboiking.entities.Driver;
import com.taxiboking.project.taxiboiking.entities.Payment;
import com.taxiboking.project.taxiboiking.entities.enums.PaymentStatus;
import com.taxiboking.project.taxiboiking.entities.enums.TransactionMethod;
import com.taxiboking.project.taxiboiking.repositories.PaymentRepository;
import com.taxiboking.project.taxiboiking.services.PaymentService;
import com.taxiboking.project.taxiboiking.services.WalletService;
import com.taxiboking.project.taxiboiking.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashpaymentStrategy implements PaymentStrategy {
    private final WalletService walletService;
    private final PaymentRepository paymentRepository;


    @Override
    public void processPayment(Payment payment) {

        Driver driver = payment.getRide().getDriver();

        double platformCommission = payment.getAmount()*PLATFORM_COMISSION;

        walletService.deductMoneyFromWallet(driver.getUser(),platformCommission,null,payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
