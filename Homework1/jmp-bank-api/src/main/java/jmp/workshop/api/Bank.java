package jmp.workshop.api;

import jmp.workshop.dto.BankCard;
import jmp.workshop.dto.BankCardType;
import jmp.workshop.dto.User;

public interface Bank {

    BankCard createBankCard(User user, BankCardType bankCardType);
}
