package org.example.util;

import org.example.container.ComponentContainer;

public class ActionUtil {
    public static String getAction() {
        System.out.println("Enter action : ");
        return ComponentContainer.stringScanner.next();
    }
}
