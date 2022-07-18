package homework5.workshop;

import lombok.Value;

@Value
// @SuppressWarnings({"java:S106"})
public class Cat implements Pet {

    private String name;

    @Override
    public void voice() {
        System.out.println("Miouu!");
    }
}
