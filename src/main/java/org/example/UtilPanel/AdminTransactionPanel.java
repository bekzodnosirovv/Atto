package org.example.UtilPanel;


import org.example.service.CardService;
import org.example.service.TransactionService;
import org.example.util.ActionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminTransactionPanel {
    @Autowired
    private TransactionService transactionService;
    @Autowired
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
