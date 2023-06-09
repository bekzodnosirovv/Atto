package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.Enums.ComponentStatus;
import org.example.Enums.TransactionType;
import org.example.container.ComponentContainer;
import org.example.dto.Card;
import org.example.dto.Profile;
import org.example.dto.Terminal;
import org.example.dto.Transaction;
import org.example.repository.TransactionRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class TransactionService {
    private TransactionRepository transactionRepository;

    public void trReFill_SV(Card card, double balance) {
        if (card.getStatus().equals(ComponentStatus.BLOCK)) {
            System.out.println("This card is blocked.");
            return;
        }
        Transaction tr = new Transaction();
        tr.setCard_number(card.getNumber());
        tr.setAmount(balance);
        tr.setType(TransactionType.ReFill);
        tr.setCreated1_date(LocalDateTime.now());
        if (transactionRepository.addReFillRP(tr, balance)) System.out.println("Success");
        else System.out.println("Error transaction. Try again.");

    }

    public void makePaymentSV(Card card, Terminal term) {
        if (card.getStatus().equals(ComponentStatus.BLOCK)) {
            System.out.println("This card is blocked.");
            return;
        }
        if (card.getBalance() < ComponentContainer.fairy) {
            System.out.println("There is not enough money on the card.");
            return;
        }

        Transaction tr = new Transaction();
        tr.setCard_number(card.getNumber());
        tr.setAmount(ComponentContainer.fairy);
        tr.setTerm_code(term.getCode());
        tr.setType(TransactionType.Payment);
        tr.setCreated1_date(LocalDateTime.now());
        if (!transactionRepository.addPaymentRP(tr)) System.out.println("Success");
        else System.out.println("Error transaction. Try again.");

    }

    public List<StringBuilder> getProfTrListSV(Profile profile) {
        return transactionRepository.getProfTrListRP(profile.getPhone());
    }

    public void getAllListSV() {
        List<Transaction> trList = transactionRepository.getAllRP();
        if (!trList.isEmpty()) trList.forEach(System.out::println);
        else System.out.println("No Transaction history.");
    }

    public void getTransactionBySV(Integer number) {
        List<Transaction> list = transactionRepository.getAllByRP(number);
        if (!list.isEmpty()) list.forEach(System.out::println);
        else System.out.println("No Transaction.");


    }

    public void getTodayPaySV() {
        List<Transaction> list = transactionRepository.getDailyTrRP(String.valueOf(LocalDate.now()));
        if (!list.isEmpty()) list.forEach(System.out::println);
        else System.out.println("No Transaction.");
    }

    public void getInterimPaySV(String fromDate, String toDate) {
        List<Transaction> list = transactionRepository.getInterimTrRP(fromDate, toDate);
        if (!list.isEmpty()) list.forEach(System.out::println);
        else System.out.println("No Transaction.");
    }

    public void getDailyPaySV(String date) {
        List<Transaction> list = transactionRepository.getDailyTrRP(date);
        if (!list.isEmpty()) list.forEach(System.out::println);
        else System.out.println("No Transaction.");
    }
}
