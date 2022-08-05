package jmp.workshop.homework6.service;

public interface TemplateGeneratorService {

    String process(String template, String value) throws IllegalArgumentException;
}
