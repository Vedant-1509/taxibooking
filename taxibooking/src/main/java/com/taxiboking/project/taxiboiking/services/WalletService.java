package com.taxiboking.project.taxiboiking.services;

import com.taxiboking.project.taxiboiking.entities.Ride;
import com.taxiboking.project.taxiboiking.entities.User;
import com.taxiboking.project.taxiboiking.entities.Wallet;
import com.taxiboking.project.taxiboiking.entities.enums.TransactionMethod;

public interface WalletService {
    Wallet addMoneyToWallet(User user, Double amount , String transactionId , Ride ride , TransactionMethod transactionMethod);

    Wallet deductMoneyFromWallet(User user, Double amount , String transactionId , Ride ride , TransactionMethod transactionMethod);
    void withdrawAllMyMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);
    Wallet findByUser(User user);
}
