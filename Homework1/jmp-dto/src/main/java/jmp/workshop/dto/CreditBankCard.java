package jmp.workshop.dto;

import lombok.*;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CreditBankCard extends BankCard {

    private final BankCardType bankCardType = BankCardType.CREDIT;

    public CreditBankCard(String number, User user) {
        super(number, user);
    }
}
