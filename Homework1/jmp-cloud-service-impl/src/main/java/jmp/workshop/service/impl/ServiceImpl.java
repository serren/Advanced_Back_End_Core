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

public class ServiceImpl extends DefaultServiceImpl {
    @Override
    public List<User> getAllUsers() {
        return super.getAllUsers()
                .stream()
                .filter(Service::isPayableUser)
                .collect(Collectors.toUnmodifiableList());
    }
}
