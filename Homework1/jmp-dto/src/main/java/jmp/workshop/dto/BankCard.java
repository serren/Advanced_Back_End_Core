package jmp.workshop.dto;

import lombok.Data;

@Data
public abstract class BankCard {

    private final String number;
    private final User user;

}
