import jmp.cloud.bank.impl.BankImpl;
import jmp.workshop.api.Bank;

module jmp.cloud.bank.impl {

    requires jmp.dto;
    requires transitive jmp.bank.api;
    requires org.apache.commons.lang3;
    requires java.validation;

    exports jmp.cloud.bank.impl;

    provides Bank with BankImpl;
}