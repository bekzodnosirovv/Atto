package org.example.UtilPanel;

import lombok.Getter;
import lombok.Setter;
import org.example.container.ComponentContainer;
import org.example.service.TransactionService;
import org.example.util.ActionUtil;
@Setter
@Getter
public class AdminStatisticPanel {
    private TransactionService transactionService;
    public void start() {
        while (true) {
            menu();
            switch (ActionUtil.getAction()) {
                case "1" -> todayPayment();
                case "2" -> interPayment();
                case "3" -> dailyPayment();
                case "4" -> transactionByTerm();
                case "5" -> transactionByCard();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Please select the given menu.");
            }
        }
    }

    private void menu() {
        System.out.println("\n***  Admin Statistic Menu ***\n");
        System.out.println("1. Today's Payments");
        System.out.println("2. Interim Payments");
        System.out.println("3. Daily payments");
        System.out.println("4. Transaction by Terminal");
        System.out.println("5. Transaction By Card");
        System.out.println("0. Exit");
    }

    private void todayPayment() {
        transactionService.getTodayPaySV();
    }

    private void interPayment() {
        System.out.println("Enter FromDate :        (yyyy-MM-dd)");
        String fromDate = ComponentContainer.stringScanner.next();
        System.out.println("Enter ToDate :          (yyyy-MM-dd)");
        String toDate = ComponentContainer.stringScanner.next();
        transactionService.getInterimPaySV(fromDate, toDate);
    }

    private void dailyPayment() {
        System.out.println("Enter date :       (yyyy-MM-dd)");
        transactionService.getDailyPaySV(ComponentContainer.stringScanner.next());
    }

    private void transactionByTerm() {
        System.out.println("Enter terminal code : ");
        transactionService.getTransactionBySV(ComponentContainer.intScanner.nextInt());
    }

    private void transactionByCard() {
        System.out.println("Enter card number : ");
        transactionService.getTransactionBySV(ComponentContainer.intScanner.nextInt());
    }


}
