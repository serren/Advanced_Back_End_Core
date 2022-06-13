package jmp.runner;

import jmp.cloud.bank.impl.BankImpl;
import jmp.workshop.api.Bank;
import jmp.workshop.dto.BankCard;
import jmp.workshop.dto.BankCardType;
import jmp.workshop.dto.Subscription;
import jmp.workshop.dto.User;
import jmp.workshop.service.Service;
import jmp.workshop.service.impl.ServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        // Points 1-17
        Bank bank = new BankImpl();
        User user = new User("John", "Doe", LocalDate.of(1990, 1,1));
        BankCard creditCard = bank.createBankCard(user, BankCardType.CREDIT);
        System.out.println("Credit card issued: " + creditCard);
        BankCard debitCard = bank.createBankCard(user, BankCardType.DEBIT);
        System.out.println("Debit card issued: " + debitCard);
        Service service = new ServiceImpl();
        service.subscribe(creditCard);
        service.subscribe(debitCard);
        User user2 = new User("Alice", "Cooper", LocalDate.of(2010, 2,2));
        creditCard = bank.createBankCard(user2, BankCardType.CREDIT);
        System.out.println("Credit card issued: " + creditCard);
        debitCard = bank.createBankCard(user2, BankCardType.DEBIT);
        System.out.println("Debit card issued: " + debitCard);
        service.subscribe(creditCard);
        service.subscribe(debitCard);
        var subscription = service.getSubscriptionByBankCardNumber(creditCard.getNumber());
        System.out.println("Subscribed users:");
        System.out.println(service.getAllUsers());
        System.out.println("Subscription:");
        System.out.println(subscription.orElseGet(() -> new Subscription("No card", LocalDate.now())));

        // Points 18-20
        System.out.println("User1 age is : " + user.getUserAge());
        System.out.println("User1 is payable : " + Service.isPayableUser(user));
        System.out.println("User2 age is : " + user2.getUserAge());
        System.out.println("User2 is payable : " + Service.isPayableUser(user2));
        System.out.println("Average age of users is: " + service.getAverageUsersAge());
        List<User> payableUsers = service.getAllUsers()
                .stream()
                .filter(Service::isPayableUser)
                .collect(Collectors.toUnmodifiableList());
        System.out.println("Payable users are:");
        System.out.println(payableUsers);
    }
}
