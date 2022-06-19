package homework2.task2;

import java.util.Collection;
import java.util.Map;

public class SquareCalculator extends Calculator {

    public SquareCalculator(Collection<Integer> values) {
        super(values);
    }

    @Override
    protected void calculate() {
        int sum = values.stream().mapToInt(v -> v * v).sum();
        System.out.printf("SquareCalculator: Sum of square values is %s\n", sum);
    }
}
