package jmp.workshop.service.impl;

import jmp.workshop.dto.BankCard;
import jmp.workshop.dto.Subscription;
import jmp.workshop.dto.User;
import jmp.workshop.service.Service;
import jmp.workshop.service.exception.SubscriptionNotFoundException;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ServiceImpl implements Service {

    Map<User, Collection<Subscription>> subscriptions = new ConcurrentHashMap<>();

    @Override
    public void subscribe(BankCard card) {
        subscribe(card, LocalDate.now());
    }

    @Override
    public void subscribe(BankCard card, LocalDate date) {
        subscriptions
                .computeIfAbsent(card.getUser(), user -> new HashSet<>())
                .add(new Subscription(card.getNumber(), date));
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String number) {
        return Optional.ofNullable(subscriptions.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(s -> s.getBankcard().equals(number))
                .findFirst()
                .orElseThrow(() -> new SubscriptionNotFoundException("Unable to find card with number: " + number)));
    }

    @Override
    public List<User> getAllUsers() {
        return List.copyOf(subscriptions.keySet());
    }

    @Override
    public double getAverageUsersAge() {
        return getAllUsers().stream()
                .map(User::getUserAge)
                .collect(Collectors.averagingDouble(value -> (double) value));
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> filter) {
        return subscriptions.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(filter)
                .collect(Collectors.toUnmodifiableList());
    }
}
