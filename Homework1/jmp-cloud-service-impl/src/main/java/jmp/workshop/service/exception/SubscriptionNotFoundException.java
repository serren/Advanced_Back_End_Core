package jmp.workshop.service.exception;

public class SubscriptionNotFoundException extends IllegalArgumentException {

    public SubscriptionNotFoundException(String s) {
        super(s);
    }
}
