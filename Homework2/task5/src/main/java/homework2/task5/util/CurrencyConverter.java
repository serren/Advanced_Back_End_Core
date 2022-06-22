package homework2.task5.util;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;

@UtilityClass
public class CurrencyConverter {

    public BigDecimal convert(@NonNull BigDecimal amount,
                              @NonNull BigDecimal fxRate) {
        return convert(amount, fxRate, 2);
    }

    public BigDecimal convert(@NonNull BigDecimal amount,
                              @NonNull BigDecimal fxRate,
                              int scale) {
        return amount.multiply(fxRate).setScale(scale, RoundingMode.HALF_UP);
    }
}
