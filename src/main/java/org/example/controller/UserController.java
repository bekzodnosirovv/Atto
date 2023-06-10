package org.example.controller;

import org.example.container.ComponentContainer;
import org.example.dto.Profile;
import org.example.service.UserService;
import org.example.util.ActionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserController {
    @Autowired
    private UserService userService;
    private Profile profile;

    public void setProfile(Profile profile) {
        this.profile = profile;
        start();
    }

    private void start() {
        System.out.println("*** User Menu ***");
        while (true) {
            userMenu();
            switch (ActionUtil.getAction()) {
                case "1" -> addCard();
                case "2" -> cardList();
                case "3" -> cardChangeStatus();
                case "4" -> deleteCard();
                case "5" -> reFill();
                case "6" -> makePayment();
                case "7" -> transaction();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Please select the given menu.");
            }
        }
    }

    private void userMenu() {
        System.out.println("\n*** Menu ****\n");
        System.out.println("1. Add Card");
        System.out.println("2. Card List");
        System.out.println("3. Card Change Status");
        System.out.println("4. Delete Card");
        System.out.println("5. ReFill");
        System.out.println("6. Make Payment");
        System.out.println("7. Transaction history");
        System.out.println("0. Exit");
    }

    private void addCard() {
        System.out.println("Enter card number : ");
        userService.addCardSV(profile, ComponentContainer.intScanner.nextInt());
    }

    private void cardList() {
        userService.CardListSV(profile);
    }

    private void cardChangeStatus() {
        System.out.println("Enter card number : ");
        userService.cardChangeStatusSV(profile, ComponentContainer.intScanner.nextInt());
    }

    private void deleteCard() {
        System.out.println("Enter card number : ");
        userService.deleteCardSV(profile, ComponentContainer.intScanner.nextInt());
    }

    private void reFill() {
        System.out.println("Enter card number : ");
        Integer cardNumber = ComponentContainer.intScanner.nextInt();
        System.out.println("Balance : ");
        double balance = ComponentContainer.doublScanner.nextDouble();
        userService.reFill_SV(profile, cardNumber, balance);
    }

    private void makePayment() {
        System.out.println("Enter card number : ");
        Integer cardNumber = ComponentContainer.intScanner.nextInt();
        System.out.println("Enter terminal number : ");
        Integer termNumber = ComponentContainer.intScanner.nextInt();
        userService.makePaymentSV(profile, cardNumber, termNumber);
    }

    private void transaction() {
        System.out.println("Transaction history : ");
        userService.tr_HistorySV(profile);
    }


}
