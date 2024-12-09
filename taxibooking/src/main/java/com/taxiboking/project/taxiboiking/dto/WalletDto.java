package com.taxiboking.project.taxiboiking.dto;

import com.taxiboking.project.taxiboiking.entities.User;
import com.taxiboking.project.taxiboiking.entities.WalletTransaction;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
public class WalletDto {

    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private UserDto user;

    private Double balance;


    private List<WalletTransactionDto> transactions;
}
