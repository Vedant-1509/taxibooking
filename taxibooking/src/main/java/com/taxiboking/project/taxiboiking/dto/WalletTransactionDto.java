package com.taxiboking.project.taxiboiking.dto;

import com.taxiboking.project.taxiboiking.entities.Ride;
import com.taxiboking.project.taxiboiking.entities.Wallet;
import com.taxiboking.project.taxiboiking.entities.enums.TransactionMethod;
import com.taxiboking.project.taxiboiking.entities.enums.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class WalletTransactionDto {
    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private TransactionMethod transactionMethod;
    private RideDto ride;
    private String transactionId;
    private WalletDto  wallet;
    private LocalDateTime timestamp;
}
