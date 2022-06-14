module application {

    requires jmp.bank.api;
    requires jmp.dto;
    requires jmp.service.api;

    requires lombok;

    uses jmp.workshop.service.Service;
    uses jmp.workshop.api.Bank;
}