package jmp.runner;

import jmp.workshop.api.Bank;
import jmp.workshop.dto.BankCard;
import jmp.workshop.dto.BankCardType;
import jmp.workshop.dto.Subscription;
import jmp.workshop.dto.User;
import jmp.workshop.service.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        // Points 1-17
        ServiceLoader<Service> loader = ServiceLoader.load(Service.class);
        Optional<Service> serviceFromLoader = loader.findFirst();
        if (serviceFromLoader.isEmpty()) {
            System.out.printf("Unable to locate instance of %s%n", Service.class.getName());
            return;
        }
        Service service = serviceFromLoader.get();
        System.out.printf("Found instance of %s%n", Service.class.getName());

        ServiceLoader<Bank> bankLoader = ServiceLoader.load(Bank.class);
        Optional<Bank> bankFromLoader = bankLoader.findFirst();
        if (bankFromLoader.isEmpty()) {
            System.out.printf("Unable to locate instance of %s%n", Bank.class.getName());
            return;
        }
        Bank bank = bankFromLoader.get();
        System.out.printf("Found instance of %s%n", Bank.class.getName());

        User user = new User("John", "Doe", LocalDate.of(1990, 1,1));
        BankCard creditCard = bank.createBankCard(user, BankCardType.CREDIT);
        System.out.println("Credit card issued: " + creditCard);
        BankCard debitCard = bank.createBankCard(user, BankCardType.DEBIT);
        System.out.println("Debit card issued: " + debitCard);

        service.subscribe(creditCard);
        service.subscribe(debitCard, LocalDate.of(2020, 1,1));
        User user2 = new User("Alice", "Cooper", LocalDate.of(2010, 2,2));
        creditCard = bank.createBankCard(user2, BankCardType.CREDIT);
        System.out.println("Credit card issued: " + creditCard);
        debitCard = bank.createBankCard(user2, BankCardType.DEBIT);
        System.out.println("Debit card issued: " + debitCard);
        service.subscribe(creditCard, LocalDate.of(2018, 1,1));
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
        
        // Points 21-25

        // uncomment to check exception
        // Optional<Subscription> unexistingSubscription = service.getSubscriptionByBankCardNumber("xxxx");

        LocalDate cutOffDate = LocalDate.of(2021, 1, 1);
        Predicate<Subscription> isSubscriptionBeforeDate = (s) -> s.getStartDate().isBefore(cutOffDate);
        List<Subscription> subscriptions = service.getAllSubscriptionsByCondition(isSubscriptionBeforeDate);
        System.out.printf("Subscription before %s are:%n", cutOffDate);
        System.out.println(subscriptions);


    }
}
