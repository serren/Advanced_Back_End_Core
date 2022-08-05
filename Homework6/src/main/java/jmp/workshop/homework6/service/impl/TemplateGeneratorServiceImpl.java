package jmp.workshop.homework6.service.impl;

import jmp.workshop.homework6.service.TemplateGeneratorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TemplateGeneratorServiceImpl implements TemplateGeneratorService {

    public static final String START_MARKER = "#{";
    public static final String END_MARKER = "}";
    public static final String DELIMITER = ";";

    @Override
    public String process(String template, String value) throws IllegalArgumentException {
        if (!containsPlaceholders(template)) {
            return template;
        }
        Map<String, String> placeHolders = getValues(value);
        StringBuilder result = new StringBuilder();
        int startIndex = template.indexOf(START_MARKER);
        int endIndex = template.indexOf(END_MARKER, startIndex);
        int currentPosition = 0;
        while (startIndex > 0 && endIndex > 0) {
            result.append(template.substring(currentPosition, startIndex));
            String placeHolder = template.substring(startIndex, endIndex + 1);
            if (!placeHolders.containsKey(placeHolder)) {
                throw new IllegalArgumentException("No value provided for " + placeHolder);
            }
            String placeHolderValue = placeHolders.get(placeHolder);
            result.append(placeHolderValue);
            currentPosition = endIndex + 1;
            startIndex = template.indexOf(START_MARKER, startIndex + placeHolderValue.length());
            endIndex = template.indexOf(END_MARKER, startIndex);
        }
        if (currentPosition < template.length()) {
            result.append(template.substring(currentPosition, template.length()));
        }
        return result.toString();
    }

    private Map<String, String> getValues(String value) {
        return Arrays.stream(StringUtils.defaultString(value)
                .split(DELIMITER))
                .map(p -> p.split("="))
                .map(s -> {
                    String[] values = new String[2];
                    values[0] = s[0];
                    values[1] = s.length > 1 ? s[1] : "";
                    return values;
                })
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));
    }

    private boolean containsPlaceholders(String template) {
        if (StringUtils.isBlank(template)) {
            return false;
        }
        int startIndex = template.indexOf(START_MARKER);
        if (startIndex < 0) {
            return false;
        }
        int endIndex = template.indexOf(END_MARKER, startIndex);
        return endIndex > 0;
    }
}
