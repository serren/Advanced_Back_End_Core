package homework2.task5.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class AccountTest {

    final Currency RUB = Currency.getInstance(new Locale("ru", "RU"));
    final Currency USD = Currency.getInstance(new Locale("en", "US"));

    @Test
    void test_IncreaseAccountBalance() {
        Account account = new Account(UUID.randomUUID().toString());
        account.add(new BigDecimal("10"), RUB);
        account.add(new BigDecimal("20"), RUB);
        account.add(new BigDecimal("200"), USD);

        Assertions.assertEquals(account.getBalance().get(RUB).doubleValue(), 30.0);
        Assertions.assertEquals(account.getBalance().get(USD).doubleValue(), 200.0);
    }

    @Test
    void test_NoNegativeAmountAllowed() {
        Account account = new Account(UUID.randomUUID().toString());
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> account.add(new BigDecimal("-10"), RUB));
        String expectedMessage = "Amount is less than zero!";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void test_NoNegativeBalanceAfterWithdraw() {
        Account account = new Account(UUID.randomUUID().toString());
        account.add(new BigDecimal("10"), RUB);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> account.withdraw(new BigDecimal("20"), RUB));
        String expectedMessage = "Withdrawal amount is greater than account balance!";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

}
