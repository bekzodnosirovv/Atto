package org.example.util;

import org.example.Enums.ComponentStatus;
import org.example.container.ComponentContainer;

public class StatusChooseUtil {
    public String get() {
        System.out.println("Enter status: \n1. ACTIVE\n2. NOT_ACTIVE\n3. BLOCK");
        String status = ComponentContainer.stringScanner.next();
        switch (status) {
            case "1" -> status = String.valueOf(ComponentStatus.ACTIVE);
            case "2" -> status = String.valueOf(ComponentStatus.NOT_ACTIVE);
            case "3" -> status = String.valueOf(ComponentStatus.BLOCK);
            default -> {
                System.out.println("This not status.");
                return null;
            }
        }
        return status;
    }
}
