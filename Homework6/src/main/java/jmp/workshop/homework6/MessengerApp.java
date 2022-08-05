package jmp.workshop.homework6;

import jmp.workshop.homework6.service.TemplateGeneratorService;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessengerApp implements CommandLineRunner {
    private final static Logger LOGGER = LoggerFactory.getLogger(MessengerApp.class);

    @Autowired
    private TemplateGeneratorService service;

    @Override
    public void run(String... args) {
        LOGGER.info("Starting applicaton");

        Options options = new Options();
        Option templateOption = new Option("t", "template", true, "Template string");
        templateOption.setRequired(false);
        options.addOption(templateOption);
        Option valuesOption = new Option("v", "values", true, "Values string");
        valuesOption.setRequired(false);
        options.addOption(valuesOption);
        HelpFormatter formatter = new HelpFormatter();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("User Profile Info", options);
            System.exit(1);
            return;
        }
        String template = cmd.getOptionValue("template");
        LOGGER.info("Template string is: " + template);
        String values = cmd.getOptionValue("values");
        LOGGER.info("Values string is: " + values);

        try {
            LOGGER.info("Result string is: " + service.process(template, values));
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("Finished applicaton");
    }

    public static void main(String[] args) {
        SpringApplication.run(MessengerApp.class, args);
    }
}