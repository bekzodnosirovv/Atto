package org.example.service;


import org.example.Enums.ComponentStatus;
import org.example.dto.Card;
import org.example.dto.Profile;
import org.example.dto.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private CardService cardService;
    @Autowired
    private TerminalService terminalService;
    @Autowired
    private TransactionService transactionService;

    public void addCardSV(Profile profile, Integer cardNumber) {
        Card card = cardService.getCardSV(cardNumber);
        if (card != null) {
            if (card.getPhone() == null) {
                card.setPhone(profile.getPhone());
                card.setStatus(ComponentStatus.ACTIVE);
                cardService.cardUpdateSV(card);
            } else System.out.println("Card busy");
        } else System.out.println("No Card");
    }

    public void CardListSV(Profile profile) {
        List<Card> cardList = cardService.getAllCardProfileSV(profile);
        if (!cardList.isEmpty()) cardList.forEach(card -> System.out.println(card.cardP()));
        else System.out.println("No Card List");
    }

    public void cardChangeStatusSV(Profile profile, Integer cardNumber) {
        Card card = cardService.getCardSV(cardNumber);
        if (card != null && card.getPhone().equals(profile.getPhone())) {
            if (card.getStatus().equals(ComponentStatus.ACTIVE)) card.setStatus(ComponentStatus.NOT_ACTIVE);
            else if (card.getStatus().equals(ComponentStatus.NOT_ACTIVE)) card.setStatus(ComponentStatus.ACTIVE);
            cardService.cardUpdateSV(card);
        } else System.out.println("No Card");
    }

    public void deleteCardSV(Profile profile, Integer cardNumber) {
        Card card = cardService.getCardSV(cardNumber);
        if (card != null && card.getPhone().equals(profile.getPhone())) {
            card.setPhone(null);
            card.setBalance(0);
            card.setStatus(ComponentStatus.BLOCK);
            cardService.cardUpdateSV(card);
        } else System.out.println("No Card.");
    }

    public void reFill_SV(Profile profile, Integer cardNumber, double balance) {
        Card card = cardService.getCardSV(cardNumber);
        if (card != null && card.getPhone().equals(profile.getPhone())) {
            transactionService.trReFill_SV(card, balance);
        } else System.out.println("No Card");
    }

    public void makePaymentSV(Profile profile, Integer cardNumber, Integer termNumber) {
        Card card = cardService.getCardSV(cardNumber);
        Terminal term = terminalService.getTermSV(termNumber);
        if (card != null && term != null) {
            if (card.getPhone().equals(profile.getPhone())) {
                transactionService.makePaymentSV(card, term);
            } else System.out.println("No Card");
        } else System.out.println("Error. Try again.");
    }

    public void tr_HistorySV(Profile profile) {
        List<StringBuilder> list = transactionService.getProfTrListSV(profile);
        if (!list.isEmpty()) list.forEach(s -> System.out.println(s.toString()));
        else System.out.println("No Transaction history.");
    }
}
