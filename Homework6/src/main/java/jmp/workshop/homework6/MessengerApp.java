package jmp.workshop.homework6;

import jmp.workshop.homework6.service.TemplateGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessengerApp implements CommandLineRunner {
    private final static Logger LOGGER = LoggerFactory.getLogger(MessengerApp.class);

    private TemplateGeneratorService service;

    @Override
    public void run(String... args) {
        LOGGER.info("Starting applicaton");

        LOGGER.info("Finished applicaton");
    }

    public static void main(String[] args) {
        SpringApplication.run(MessengerApp.class, args);
    }
}