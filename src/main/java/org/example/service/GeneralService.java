package org.example.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Enums.ComponentStatus;
import org.example.Enums.ProfileRole;
import org.example.controller.AdminController;
import org.example.controller.UserController;
import org.example.dto.Profile;
import org.example.repository.ProfileRepository;
import org.example.util.ActionUtil;
import org.example.util.PhoneCheckUtil;

import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
public class GeneralService {
    AdminController adminController;
    ProfileRepository profileRepository;

    public void logHandleSV(String phone, String password) {
        if (!PhoneCheckUtil.checkPhone(phone)) {
            System.out.println("Phone number format is incorrect.");
            return;
        }
        Profile profile = profileRepository.getProfileRP(phone);
        if (profile == null) {
            System.out.println("Sorry, You are not registered.");
            return;
        }
        if (profile.getStatus().equals(ComponentStatus.BLOCK)) {
            System.out.println("This profile is blocked.");
            return;
        }
        if (!profile.getPassword().equals(password)) {
            System.out.println("Password error.");
            return;
        }
        if (profile.getRole().equals(ProfileRole.ADMIN)) {
            System.out.println("""
                    Menu :
                    1. Admin Menu
                    2. User Menu""");
            switch (ActionUtil.getAction()) {
                case "1" -> adminController.start();
                case "2" -> new UserController(profile).start();
            }
        } else new UserController(profile).start();
    }

    public void regHandleSV(String name, String surname, String phone, String password) {
        if (!PhoneCheckUtil.checkPhone(phone)) {
            System.out.println("Phone number format is incorrect.");
            return;
        }
        Profile profile = profileRepository.getProfileRP(phone);
        if (profile != null) {
            System.out.println("This phone is registered.");
            return;
        }
        profile = new Profile(name, surname, phone, password, LocalDateTime.now(), ComponentStatus.ACTIVE, ProfileRole.USER);
        if (profileRepository.addProfileRP(profile)) {
            System.out.println("<< Success >>");
            new UserController(profile).start();
        } else System.out.println("Error. Try again.");
    }

}
