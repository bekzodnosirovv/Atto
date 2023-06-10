package org.example.UtilPanel;

import org.example.container.ComponentContainer;
import org.example.service.TerminalService;
import org.example.util.ActionUtil;
import org.example.util.StatusChooseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminTerminalPanel {
    @Autowired
    private TerminalService terminalService;
    @Autowired
    private StatusChooseUtil statusChooseUtil;

    public void start() {
        while (true) {
            menu();
            switch (ActionUtil.getAction()) {
                case "1" -> createTerminal();
                case "2" -> terminalList();
                case "3" -> updateTerminal();
                case "4" -> changeTerminalStatus();
                case "5" -> deleteTerminal();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Please select the given menu.");
            }
        }
    }

    private void menu() {
        System.out.println("\n*** Admin Terminal Menu ***\n");
        System.out.println("1. Create Terminal");
        System.out.println("2. Terminal List");
        System.out.println("3. Update Terminal");
        System.out.println("4. Change Terminal Status");
        System.out.println("5. Delete Terminal");
        System.out.println("0. Exit");
    }

    private void createTerminal() {
        System.out.println("Enter terminal code : ");
        Integer cardNumber = ComponentContainer.intScanner.nextInt();
        System.out.println("Enter address : ");
        String address = ComponentContainer.stringScanner.next();
        terminalService.createdTermSV(cardNumber, address);
    }

    private void terminalList() {
        terminalService.getAllTermSV();
    }

    private void updateTerminal() {
        System.out.println("Enter terminal code : ");
        Integer termNumber = ComponentContainer.intScanner.nextInt();
        System.out.println("Enter new address : ");
        String address = ComponentContainer.stringScanner.next();
        terminalService.updateTermSV(termNumber, address);
    }

    private void changeTerminalStatus() {
        System.out.println("Enter terminal code : ");
        Integer termNumber = ComponentContainer.intScanner.nextInt();
        String status = statusChooseUtil.get();
        terminalService.changeTermStatusSV(termNumber, status);
    }

    private void deleteTerminal() {
        System.out.println("Enter terminal code : ");
        terminalService.deleteTermSV(ComponentContainer.intScanner.nextInt());
    }
}
