package jmp.workshop.service;

import jmp.workshop.dto.BankCard;
import jmp.workshop.dto.Subscription;
import jmp.workshop.dto.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface Service {
    int ADULT_AGE = 18;

    void subscribe(BankCard card);

    void subscribe(BankCard card, LocalDate date);

    Optional<Subscription> getSubscriptionByBankCardNumber(String number);

    List<User> getAllUsers();

    default double getAverageUsersAge() {
        return getAllUsers().stream()
                .map(User::getUserAge)
                .collect(Collectors.averagingDouble(value -> (double) value));
    }

    static boolean isPayableUser(User user) {
        return user.getUserAge() > ADULT_AGE;
    }

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> filter);
}
