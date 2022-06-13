package jmp.workshop.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class Subscription {

    String bankcard;
    LocalDate startDate;
}
