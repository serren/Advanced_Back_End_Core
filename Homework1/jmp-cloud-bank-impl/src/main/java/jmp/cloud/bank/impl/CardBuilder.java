package jmp.cloud.bank.impl;

import jmp.workshop.dto.*;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class CardBuilder {

    private String number;
    private User user;

    private BankCardType bankCardType;

    private CardBuilder() {
    }

    public CardBuilder number(String number) {
        this.number = number;
        return this;
    }

    public CardBuilder user(@NotNull User user) {
        this.user = user;
        return this;
    }

    public CardBuilder type(@NotNull BankCardType bankCardType) {
        this.bankCardType = bankCardType;
        return this;
    }

    public BankCard build() {
        String number = StringUtils.defaultString(this.number, UUID.randomUUID().toString());
        if (BankCardType.CREDIT == bankCardType) {
            return new CreditBankCard(number, user);
        } else if (BankCardType.DEBIT == bankCardType) {
            return new DebitBankCard(number, user);
        }
        throw new IllegalArgumentException("Unknown card type!");
    }

    public static CardBuilder getInstance() {
        return new CardBuilder();
    }
}
