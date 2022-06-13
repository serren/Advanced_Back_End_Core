package jmp.workshop.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {

    private final String name;
    private final String surname;
    private final LocalDate birthday;
}
