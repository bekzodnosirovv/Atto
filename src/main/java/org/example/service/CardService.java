package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.Enums.ComponentStatus;
import org.example.container.ComponentContainer;
import org.example.dto.Card;
import org.example.dto.Profile;
import org.example.repository.CardRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Setter
@Getter
public class CardService {
    private CardRepository cardRepository;

    public void createdCardSV(Integer cardNumber, LocalDate exp_date) {
        Card card = getCardSV(cardNumber);
        if (card == null) {
            card = new Card();
            card.setNumber(cardNumber);
            card.setExp_date(exp_date);
            card.setCreated_date(LocalDateTime.now());
            card.setStatus(ComponentStatus.CREATED);
            if (cardRepository.add(card)) System.out.println("Success");
            else System.out.println("Error. Try again.");
        } else System.out.println("This card is available.");

    }

    public void cardUpdateSV(Card card) {
        if (cardRepository.cardUpdateRP(card)) System.out.println("Success");
        else System.out.println("Error. Try again.");

    }

    public Card getCardSV(Integer cardNumber) {
        return cardRepository.getCardRP(cardNumber);
    }

    public List<Card> getAllCardProfileSV(Profile profile) {
        return cardRepository.getProfileCardListRP(profile);
    }

    public void getAllCardSV() {
        List<Card> cardList = cardRepository.getAllCardRP();
        if (!cardList.isEmpty()) cardList.forEach(System.out::println);
        else System.out.println("No Card List");
    }

    public void updateCardSV(Integer cardNumber, LocalDate exp_date) {
        Card card = getCardSV(cardNumber);
        if (card != null) {
            card.setExp_date(exp_date);
            if (cardRepository.cardUpdateRP(card)) System.out.println("Success");
            else System.out.println("Error. Try again.");
        } else System.out.println("No Card");
    }

    public void changeCardStatusSV(Integer cardNumber, String status) {
        if (status == null) return;
        Card card = getCardSV(cardNumber);
        if (card != null) {
            card.setStatus(ComponentStatus.valueOf(status));
            if (cardRepository.cardUpdateRP(card)) System.out.println("Success");
            else System.out.println("Error. Try again.");
        } else System.out.println("No Card");
    }

    public void deleteCardSV(Integer cardNumber) {
        Card card = getCardSV(cardNumber);
        if (card != null) {
            if (cardRepository.deleteCardRP(card)) System.out.println("Success");
            else System.out.println("Error. Try again.");
        } else System.out.println("No Card");
    }

    public void cardCompanyBalanceSV() {
        Card card = getCardSV(ComponentContainer.termCardNumber);
        System.out.println("Balance : " + card.getBalance());
    }
}
