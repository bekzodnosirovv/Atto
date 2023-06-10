package org.example.UtilPanel;

import org.example.container.ComponentContainer;
import org.example.service.ProfileService;
import org.example.util.ActionUtil;
import org.example.util.StatusChooseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminProfilePanel {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private StatusChooseUtil statusChooseUtil;

    public void start() {
        while (true) {
            menu();
            switch (ActionUtil.getAction()) {
                case "1" -> profileList();
                case "2" -> changeProfileStatus();
                case "3" -> changeProfileRole();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Please select the given menu.");
            }
        }
    }

    private void menu() {
        System.out.println("\n***  Admin Profile Menu  ***\n");
        System.out.println("1. Profile List");
        System.out.println("2. Change Profile Status");
        System.out.println("3. Change Profile Role");
        System.out.println("0. Exit");
    }

    private void profileList() {
        profileService.getProfListSV();
    }

    private void changeProfileStatus() {
        System.out.println("Enter profile phone : ");
        String phone = ComponentContainer.stringScanner.next();
        String status = statusChooseUtil.get();
        profileService.changeProfileStatusSV(phone, status);
    }

    private void changeProfileRole() {
        System.out.println("Enter profile phone : ");
        profileService.changeProfileRoleSV(ComponentContainer.stringScanner.next());
    }
}
