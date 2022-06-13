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

public class App {

    public static void main(String[] args) {
        Bank bank = new BankImpl();
        User user = new User("John", "Doe", LocalDate.of(1990, 1,1));
        BankCard creditCard = bank.createBankCard(user, BankCardType.CREDIT);
        System.out.println("Credit card issued: " + creditCard);
        BankCard debitCard = bank.createBankCard(user, BankCardType.DEBIT);
        System.out.println("Debit card issued: " + debitCard);
        Service service = new ServiceImpl();
        service.subscribe(creditCard);
        service.subscribe(debitCard);
        user = new User("Alice", "Cooper", LocalDate.of(2010, 2,2));
        creditCard = bank.createBankCard(user, BankCardType.CREDIT);
        System.out.println("Credit card issued: " + creditCard);
        debitCard = bank.createBankCard(user, BankCardType.DEBIT);
        System.out.println("Debit card issued: " + debitCard);
        service.subscribe(creditCard);
        service.subscribe(debitCard);
        var subscription = service.getSubscriptionByBankCardNumber(creditCard.getNumber());
        System.out.println("Subscribed users:");
        System.out.println(service.getAllUsers());
        System.out.println("Subscription:");
        System.out.println(subscription.orElseGet(() -> new Subscription("No card", LocalDate.now())));
    }
}
