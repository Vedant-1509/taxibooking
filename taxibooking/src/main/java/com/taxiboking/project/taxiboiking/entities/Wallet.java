package com.taxiboking.project.taxiboiking.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    private User user;

    private Double balance=0.0;

    @OneToMany(mappedBy = "wallet" ,fetch = FetchType.LAZY)
    private List<WalletTransaction> transactions;
}
