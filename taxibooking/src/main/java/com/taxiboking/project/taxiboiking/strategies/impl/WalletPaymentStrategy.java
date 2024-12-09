package com.taxiboking.project.taxiboiking.strategies.impl;

import com.taxiboking.project.taxiboiking.entities.Driver;
import com.taxiboking.project.taxiboiking.entities.Payment;
import com.taxiboking.project.taxiboiking.entities.Rider;
import com.taxiboking.project.taxiboiking.entities.enums.PaymentStatus;
import com.taxiboking.project.taxiboiking.entities.enums.TransactionMethod;
import com.taxiboking.project.taxiboiking.repositories.PaymentRepository;
import com.taxiboking.project.taxiboiking.services.PaymentService;
import com.taxiboking.project.taxiboiking.services.WalletService;
import com.taxiboking.project.taxiboiking.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy{

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;


    @Override
    @Transactional
    public void processPayment(Payment payment) {

        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        walletService.deductMoneyFromWallet(rider.getUser(),
                payment.getAmount(), null,payment.getRide(), TransactionMethod.RIDE);

        double driversCut = payment.getAmount()- (1-PLATFORM_COMISSION);

        walletService.addMoneyToWallet(driver.getUser(),driversCut,null,payment.getRide(),TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);

    }
}
