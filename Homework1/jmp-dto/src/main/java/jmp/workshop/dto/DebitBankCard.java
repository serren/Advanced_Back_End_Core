package jmp.workshop.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DebitBankCard extends BankCard {

    private final BankCardType bankCardType = BankCardType.DEBIT;

    public DebitBankCard(String number, User user) {
        super(number, user);
    }
}
