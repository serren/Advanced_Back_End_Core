package jmp.cloud.bank.impl;

import jmp.workshop.api.Bank;
import jmp.workshop.dto.*;

import java.util.UUID;

public class BankImpl implements Bank {

    @Override
    public BankCard createBankCard(User user, BankCardType bankCardType) {
        if (BankCardType.CREDIT == bankCardType) {
            return new CreditBankCard(UUID.randomUUID().toString(), user);
        } else if (BankCardType.DEBIT == bankCardType) {
            return new DebitBankCard(UUID.randomUUID().toString(), user);
        }
        throw new IllegalArgumentException("Unknown card type!");
    }
}
