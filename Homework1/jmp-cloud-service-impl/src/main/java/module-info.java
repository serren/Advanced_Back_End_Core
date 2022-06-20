import jmp.workshop.service.Service;
import jmp.workshop.service.impl.DefaultServiceImpl;
import jmp.workshop.service.impl.ServiceImpl;

module jmp.cloud.service.impl {

    requires jmp.dto;
    requires transitive jmp.service.api;

    exports jmp.workshop.service.impl;

    provides Service with
            DefaultServiceImpl,
            ServiceImpl;

}