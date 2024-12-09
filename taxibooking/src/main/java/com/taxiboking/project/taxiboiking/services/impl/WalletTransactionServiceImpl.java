package com.taxiboking.project.taxiboiking.services.impl;

import com.taxiboking.project.taxiboiking.dto.WalletTransactionDto;
import com.taxiboking.project.taxiboiking.entities.WalletTransaction;
import com.taxiboking.project.taxiboiking.repositories.WalletTransactionRepository;
import com.taxiboking.project.taxiboiking.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;
    private final ModelMapper modelMapper;
    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {

        walletTransactionRepository.save(walletTransaction);

    }

}
