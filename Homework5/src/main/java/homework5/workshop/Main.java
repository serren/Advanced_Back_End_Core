package homework5.workshop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// @SuppressWarnings({"java:S128", "java:S120", "java:S4524", "java:S106", "java:S1301", "java:S1612"})
public class Main {
    
    public static void main(String[] args) {

        List<Pet> pets = new ArrayList<>();

        pets.add(new Dog("Sharik"));
        pets.add(new Cat("Matrosskin"));

        pets.forEach(p -> p.voice());

        String petType = pets.get(0).getClass().getSimpleName();

        switch(petType) {

            default:
                System.out.println("Unknown pet!");

            case "Snake":
                System.out.println("Shhh!");
                break;

        }
    }
}