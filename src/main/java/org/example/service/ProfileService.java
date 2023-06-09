package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.Enums.ComponentStatus;
import org.example.Enums.ProfileRole;
import org.example.dto.Profile;
import org.example.repository.ProfileRepository;

import java.util.List;

@Setter
@Getter
public class ProfileService {
    ProfileRepository profileRepository;

    public void getProfListSV() {
        List<Profile> profileList = profileRepository.getAllProfileRP();
        if (!profileList.isEmpty()) profileList.forEach(System.out::println);
        else System.out.println("No Profile List");
    }

    public void changeProfileRoleSV(String phone) {
        Profile profile = profileRepository.getProfileRP(phone);
        if (profile != null) {
            if (profile.getRole().equals(ProfileRole.USER)) profile.setRole(ProfileRole.ADMIN);
            else if (profile.getRole().equals(ProfileRole.ADMIN)) profile.setRole(ProfileRole.USER);
            if (profileRepository.updateProfileRP(profile)) System.out.println("Success");
            else System.out.println("Error. Try again.");
        } else System.out.println("No Profile");

    }

    public void changeProfileStatusSV(String phone, String status) {
        if (status == null) return;
        Profile profile = profileRepository.getProfileRP(phone);
        if (profile != null) {
            profile.setStatus(ComponentStatus.valueOf(status));
            if (profileRepository.updateProfileRP(profile)) System.out.println("Success");
            else System.out.println("Error. Try again.");
        } else System.out.println("No Profile");
    }
}
