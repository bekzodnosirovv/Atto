package org.example.dto;

import lombok.*;
import org.example.Enums.ComponentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Card {

    private Integer number;
    private LocalDate exp_date;
    private double balance;
    private ComponentStatus status;
    private String phone;
    private LocalDateTime created_date;

    public String cardP() {
        return "Card Number : "+number + ",  Expire date : " + exp_date + ",  Balance : " + balance;
    }
}
