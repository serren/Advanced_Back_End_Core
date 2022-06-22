package homework2.task5;

import homework2.task5.model.Account;
import homework2.task5.util.CurrencyConverter;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

public class App {

    public static void main(String[] args) {
        Currency rub = Currency.getInstance(new Locale("ru", "RU"));
        Currency usd = Currency.getInstance(new Locale("en", "US"));
        Account account = new Account(UUID.randomUUID().toString());

        account.add(new BigDecimal("10"), rub);
        account.add(new BigDecimal("200"), usd);
        account.withdraw(new BigDecimal("50"), usd);

        System.out.println(account);

        BigDecimal convertedValue = CurrencyConverter.convert(new BigDecimal("10"), new BigDecimal("10"));

        System.out.println(convertedValue);
    }
}