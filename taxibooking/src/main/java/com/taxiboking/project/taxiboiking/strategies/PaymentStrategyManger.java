package com.taxiboking.project.taxiboiking.strategies;

import com.taxiboking.project.taxiboiking.entities.enums.PaymentMethod;
import com.taxiboking.project.taxiboiking.strategies.impl.CashpaymentStrategy;
import com.taxiboking.project.taxiboiking.strategies.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentStrategyManger {
    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashpaymentStrategy cashpaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod){

         return switch (paymentMethod){
            case WALLET -> walletPaymentStrategy;
             case CASH -> cashpaymentStrategy;
             default -> throw new RuntimeException("Invalid payment method");
        };

    }

}
