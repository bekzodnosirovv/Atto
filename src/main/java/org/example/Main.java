package org.example;

import org.example.controller.GeneralController;
import org.example.util.CreateDataBase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public static void main(String[] args) {

        new CreateDataBase().start();
        new GeneralController().start();

    }
}
