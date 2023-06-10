package org.example.controller;

import lombok.Setter;
import org.example.container.ComponentContainer;
import org.example.service.GeneralService;
import org.example.util.ActionUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

@Setter
@Controller
public class GeneralController {

    ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
    GeneralService generalService = (GeneralService) context.getBean("generalService");

    public void start() {
        System.out.println("**** ATTO ****\n");
        while (true) {
            menu();
            switch (ActionUtil.getAction()) {
                case "1" -> login();
                case "2" -> registration();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Please select the given menu.");
            }
        }
    }

    private void menu() {
        System.out.println("*** Menu ***");
        System.out.println("""
                1. Login
                2. Registration
                0. Exit""");
    }

    private void login() {
        System.out.println("Login\n");
        System.out.println("Enter phone : ");
        String phone = ComponentContainer.stringScanner.next();
        System.out.println("Enter password : ");
        String password = ComponentContainer.stringScanner.next();
        generalService.logHandleSV(phone, password);
    }

    private void registration() {
        System.out.println("Registration\n");
        System.out.println("Enter name : ");
        String name = ComponentContainer.stringScanner.next();
        System.out.println("Enter surname : ");
        String surname = ComponentContainer.stringScanner.next();
        System.out.println("Enter phone : ");
        String phone = ComponentContainer.stringScanner.next();
        System.out.println("Enter password : ");
        String password = ComponentContainer.stringScanner.next();
        generalService.regHandleSV(name, surname, phone, password);
    }
}
