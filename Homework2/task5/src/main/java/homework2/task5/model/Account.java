package homework2.task5.model;

import lombok.Data;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@Value
public class Account {

    String name;
    ReentrantLock lock = new ReentrantLock();
    Map<Currency, BigDecimal> balance = new HashMap<>();

    public Map<Currency, BigDecimal> getBalance() {
        return Collections.unmodifiableMap(this.balance);
    }

    /**
     * Adds given amount to account balance
     *
     * @param amount - value to add
     */
    public void add(@NonNull BigDecimal amount, @NonNull Currency currency) {
        if (amount.doubleValue() < 0.0) {
            throw new IllegalArgumentException("Amount is less than zero!");
        }
        try {
            lock.lock();
            balance.merge(currency, amount, BigDecimal::add);
            lock.unlock();
        }  finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
    }

    /**
     * Withdraws given amount from account balance
     *
     * @param amount - value to withdraw
     */
    public void withdraw(@NonNull BigDecimal amount, @NonNull Currency currency) {
        if (amount.doubleValue() < 0.0) {
            throw new IllegalArgumentException("Amount is less than zero!");
        }
        try {
            lock.lock();
            balance.putIfAbsent(currency, new BigDecimal("0"));
            balance.merge(currency, amount, (prevVal, newVal) -> {
                if (prevVal.compareTo(newVal) < 0) {
                    throw new IllegalArgumentException("Withdrawal amount is greater than account balance!");
                }
                return prevVal.subtract(newVal);
            });
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
    }
}
