package homework2.task2;

import java.util.Collection;
import java.util.Random;

public class Producer extends AbstractJob {
    private final Random generator = new Random();

    public Producer(Collection<Integer> values) {
         super(values);
    }

    @Override
    protected void doJob() {
        int value = generator.nextInt(100) + 1;
        values.add(value);
        System.out.printf("Producer: Added value %s\n", value);
    }
}
