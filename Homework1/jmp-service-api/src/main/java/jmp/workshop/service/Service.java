package jmp.workshop.service;

import jmp.workshop.dto.BankCard;
import jmp.workshop.dto.Subscription;
import jmp.workshop.dto.User;

import java.util.List;
import java.util.Optional;

public interface Service {
    int ADULT_AGE = 18;

    void subscribe(BankCard card);

    Optional<Subscription> getSubscriptionByBankCardNumber(String number);

    List<User> getAllUsers();

    double getAverageUsersAge();

    static boolean isPayableUser(User user) {
        return user.getUserAge() > ADULT_AGE;
    }

}
