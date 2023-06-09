package org.example.dto;

import jdk.jshell.Snippet;
import lombok.*;
import org.example.Enums.ComponentStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Terminal {
    private Integer code;
    private String address;
    private ComponentStatus status;
    private LocalDateTime crated_date;

}
