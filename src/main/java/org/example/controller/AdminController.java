package org.example.controller;


import lombok.Getter;
import lombok.Setter;
import org.example.UtilPanel.*;
import org.example.util.ActionUtil;
@Setter
@Getter
public class AdminController {
    private AdminCardPanel adminCardPanel;
    private AdminTerminalPanel adminTerminalPanel;
    private AdminProfilePanel adminProfilePanel;
    private AdminTransactionPanel adminTransactionPanel;
    private AdminStatisticPanel adminStatisticPanel;

    public void start() {
        System.out.println("**** Admin Menu ****\n");
        while (true) {
            adminMenu();
            switch (ActionUtil.getAction()) {
                case "1" -> card();
                case "2" -> terminal();
                case "3" -> profile();
                case "4" -> transaction();
                case "5" -> statistic();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Please select the given menu.");
            }
        }
    }

    private void adminMenu() {
        System.out.println("*** Menu ***");
        System.out.println("1. Card");
        System.out.println("2. Terminal");
        System.out.println("3. Profile");
        System.out.println("4. Transaction");
        System.out.println("5. Statistic");
        System.out.println("0. Exit");
    }

    private void card() {
        adminCardPanel.start();
    }

    private void terminal() {
        adminTerminalPanel.start();
    }

    private void profile() {
        adminProfilePanel.start();
    }

    private void transaction() {
        adminTransactionPanel.start();
    }

    private void statistic() {
        adminStatisticPanel.start();
    }


}
