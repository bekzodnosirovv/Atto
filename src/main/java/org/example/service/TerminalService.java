package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.Enums.ComponentStatus;
import org.example.dto.Terminal;
import org.example.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TerminalService {
    @Autowired
    private TermRepository termRepository;
    public Terminal getTermSV(Integer termNumber) {
        return termRepository.getTermRP(termNumber);
    }

    public void createdTermSV(Integer termNumber, String address) {
        Terminal term = getTermSV(termNumber);
        if (term == null) {
            term = new Terminal();
            term.setCode(termNumber);
            term.setAddress(address);
            term.setStatus(ComponentStatus.ACTIVE);
            term.setCrated_date(LocalDateTime.now());
            if (termRepository.add(term)) System.out.println("Success");
            else System.out.println("Error. Try again.");
        } else System.out.println("This terminal is available.");
    }

    public void getAllTermSV() {
        List<Terminal> terminalList = termRepository.getAllTermRP();
        if (!terminalList.isEmpty()) terminalList.forEach(System.out::println);
        else System.out.println("No Terminal List");
    }


    public void updateTermSV(Integer termNumber, String address) {
        Terminal term = getTermSV(termNumber);
        if (term != null) {
            term.setAddress(address);
            if (termRepository.termUpdateRP(term)) System.out.println("Success");
            else System.out.println("Error. Try again.");
        } else System.out.println("No Terminal");
    }

    public void changeTermStatusSV(Integer termNumber, String status) {
        if (status == null) return;
        Terminal term = getTermSV(termNumber);
        if (term != null) {
            term.setStatus(ComponentStatus.valueOf(status));
            if (termRepository.termUpdateRP(term)) System.out.println("Success");
            else System.out.println("Error. Try again.");
        } else System.out.println("No Terminal");
    }

    public void deleteTermSV(Integer termNumber) {
        Terminal term = getTermSV(termNumber);
        if (term != null) {
            if (termRepository.deleteTermRP(term)) System.out.println("Success");
            else System.out.println("Error. Try again.");
        } else System.out.println("No Terminal");
    }
}
