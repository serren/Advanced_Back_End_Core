package jmp.workshop.service.impl;

import jmp.workshop.dto.BankCard;
import jmp.workshop.dto.Subscription;
import jmp.workshop.dto.User;
import jmp.workshop.service.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceImpl implements Service {

    Map<User, Collection<Subscription>> subscriptions = new ConcurrentHashMap<>();

    @Override
    public void subscribe(BankCard card) {
        subscriptions
                .computeIfAbsent(card.getUser(), user -> new HashSet<>())
                .add(new Subscription(card.getNumber(), LocalDate.now()));
       }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String number) {
        return subscriptions.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(s -> s.getBankcard().equals(number))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return List.copyOf(subscriptions.keySet());
    }
}
