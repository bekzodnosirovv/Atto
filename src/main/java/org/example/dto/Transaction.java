package org.example.dto;

import lombok.*;
import org.example.Enums.TransactionType;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {
    private Integer card_number;
    private double amount;
    private int term_code;
    private TransactionType type;
    private LocalDateTime created1_date;


}
