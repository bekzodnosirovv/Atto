package org.example.UtilPanel;

import lombok.Getter;
import lombok.Setter;
import org.example.service.CardService;
import org.example.service.TransactionService;
import org.example.util.ActionUtil;
@Setter
@Getter
public class AdminTransactionPanel {
    private TransactionService transactionService;
    private CardService cardService;
    public void start() {
        while (true) {
            menu();
            switch (ActionUtil.getAction()) {
                case "1" -> transactionList();
                case "2" -> cardComBalance();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Please select the given menu.");
            }
        }
    }

    private void menu() {
        System.out.println("\n*** Admin Transaction Menu ***\n");
        System.out.println("1. Transaction List");
        System.out.println("2. Card Company Balance");
        System.out.println("0. Exit");
    }

    private void transactionList() {
        transactionService.getAllListSV();
    }

    private void cardComBalance() {
        cardService.cardCompanyBalanceSV();
    }
}
