package jmp.workshop.homework6.service;

import jmp.workshop.homework6.service.impl.TemplateGeneratorServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TemplateGeneratorServiceTest {

    @InjectMocks
    private TemplateGeneratorService service = new TemplateGeneratorServiceImpl();

    @Test
    public void testOnePlaceholderReplace() {
        String template = "Hello #{value}";
        String value = "#{value}=world";
        Assertions.assertEquals("Hello world", service.process(template, value));
    }

    @Test
    public void testMultiplePlaceholderReplace() {
        String template = "Hello #{value}! Hi #{value2}!";
        String value = "#{value}=world;#{value2}=buddy";
        Assertions.assertEquals("Hello world! Hi buddy!", service.process(template, value));
    }

    @Test
    public void testExceptionRaisedWhenPlaceholderMissing() {
        String template = "Hello #{value}! Hi #{value2}!";
        String value = "#{value}=world";
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.process(template, value));
    }

    @Test
    public void testMultiplePlaceholderReplaceWithUnused() {
        String template = "Hello #{value}! Hi #{value2}!";
        String value = "#{value}=world;#{value2}=buddy;#{value3}=Fred";
        Assertions.assertEquals("Hello world! Hi buddy!", service.process(template, value));
    }

    @Test
    public void testPlaceholderReplaceWithPlaceHolderValue() {
        String template = "Hello #{value}! Hi #{value2}!";
        String value = "#{value}=world;#{value2}=#{no_value}";
        Assertions.assertEquals("Hello world! Hi #{no_value}!", service.process(template, value));
    }

    @Test
    public void testExceptionEmptyValuesProvided() {
        String template = "Hello #{value}! Hi #{value2}!";
        String value = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.process(template, value));
    }
}
