package homework2.task2;

import java.util.Collection;

public class Calculator extends AbstractJob {

    protected int size = 0;

    public Calculator(Collection<Integer> values) {
        super(values);
    }
    protected void doJob() {
        if (this.size != values.size()) {
            calculate();
            this.size = values.size();
        }
    }

    protected void calculate() {
        int sum = values.stream().mapToInt(v -> v).sum();
        System.out.printf("Calculator: Sum of values is %s\n", sum);
    }
}
