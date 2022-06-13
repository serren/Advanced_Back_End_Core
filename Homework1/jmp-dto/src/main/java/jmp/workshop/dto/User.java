package jmp.workshop.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
public class User {

    private final String name;
    private final String surname;
    private final LocalDate birthday;

    /**
     * Calculates a user's age in years.
     *
     * @return user's age in years
     */
    public long getUserAge() {
        return ChronoUnit.YEARS.between(getBirthday(), LocalDate.now());
    }
}
