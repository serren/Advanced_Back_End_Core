package jmp.cloud.bank.impl;

import jmp.workshop.api.Bank;
import jmp.workshop.dto.*;

import java.util.UUID;
import java.util.function.Supplier;

public class BankImpl implements Bank {

    @Override
    public BankCard createBankCard(User user, BankCardType bankCardType) {
        CardBuilder builder = CardBuilder.getInstance().user(user).type(bankCardType);
        Supplier<BankCard> cardCreator = builder::build;
        return cardCreator.get();
    }
}
