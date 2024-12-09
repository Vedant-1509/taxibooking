package com.taxiboking.project.taxiboiking.services;

import com.taxiboking.project.taxiboiking.dto.WalletTransactionDto;
import com.taxiboking.project.taxiboiking.entities.WalletTransaction;

public interface WalletTransactionService {

    void createNewWalletTransaction(WalletTransaction walletTransaction);
}
