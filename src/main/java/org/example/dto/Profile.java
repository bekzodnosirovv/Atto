package org.example.dto;

import jdk.jshell.Snippet;
import lombok.*;
import org.example.Enums.ComponentStatus;
import org.example.Enums.ProfileRole;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Profile {
    private String name;
    private String surname;
    private String phone;
    private String password;
    private LocalDateTime created_date;
    private ComponentStatus status;
    private ProfileRole role;
}
