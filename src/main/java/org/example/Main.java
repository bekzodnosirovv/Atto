package org.example;

import org.example.config.SpringConfig;
import org.example.controller.GeneralController;
import org.example.util.CreateDataBase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class Main {
    public static void main(String[] args) {

//        new CreateDataBase().start();


        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        GeneralController generalController = (GeneralController) context.getBean("generalController");
        generalController.start();

    }
}
