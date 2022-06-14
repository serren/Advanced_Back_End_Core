import jmp.workshop.service.Service;

module application {

    requires jmp.cloud.bank.impl;
    requires jmp.dto;
    requires jmp.service.api;
    requires jmp.cloud.service.impl;

    requires lombok;

    uses Service;
}