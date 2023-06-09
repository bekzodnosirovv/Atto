package org.example.UtilPanel;

import lombok.Getter;
import lombok.Setter;
import org.example.container.ComponentContainer;
import org.example.service.CardService;
import org.example.service.TerminalService;
import org.example.util.ActionUtil;
import org.example.util.StatusChooseUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
@Setter
@Getter
public class AdminCardPanel {

    private CardService cardService;
    private TerminalService terminalService;
    private StatusChooseUtil statusChooseUtil;
    public void start() {
        while (true) {
            menu();
            switch (ActionUtil.getAction()) {
                case "1" -> createCard();
                case "2" -> cardList();
                case "3" -> updateCard();
                case "4" -> changeCardStatus();
                case "5" -> deleteCard();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Please select the given menu.");
            }
        }
    }

    private void menu() {

        System.out.println("\n***  Admin Card Menu  ***\n");
        System.out.println("1. Create Card");
        System.out.println("2. Card List");
        System.out.println("3. Update Card");
        System.out.println("4. Change Card Status");
        System.out.println("5. Delete Card");
        System.out.println("0. Exit");
    }

    private void createCard() {
        System.out.println("Enter card number : ");
        Integer cardNumber = ComponentContainer.intScanner.nextInt();
        System.out.println("Enter expire date :     (yyyy-MM-dd)");
        String exp_date = ComponentContainer.stringScanner.next();
        cardService.createdCardSV(cardNumber, LocalDate.parse(exp_date));
    }

    private void cardList() {
        cardService.getAllCardSV();
    }

    private void updateCard() {
        System.out.println("Enter card number : ");
        Integer cardNumber = ComponentContainer.intScanner.nextInt();
        System.out.println("Enter new expire date :     (yyyy-MM-dd)");
        String exp_date = ComponentContainer.stringScanner.next();
        cardService.updateCardSV(cardNumber, LocalDate.parse(exp_date));
    }

    private void changeCardStatus() {
        System.out.println("Enter card number : ");
        Integer cardNumber = ComponentContainer.intScanner.nextInt();
        String status = statusChooseUtil.get();
        cardService.changeCardStatusSV(cardNumber, status);
    }

    private void deleteCard() {
        System.out.println("Enter card number : ");
        cardService.deleteCardSV(ComponentContainer.intScanner.nextInt());
    }
}
