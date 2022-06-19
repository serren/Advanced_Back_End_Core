package homework1.task1;

import java.util.Iterator;
import java.util.Map;

public class Calculator extends Thread {
    private final Map<Integer, Integer> values;

    public Calculator(Map<Integer, Integer> values) {
        this.values = values;
    }

    @Override
    public void run() {
        for (var i = 0; i < 100; i++) {
            try {
                Thread.sleep(1);
                //int sum = sumUsingEntrySetIterator(values);
                //int sum = sumUsingEntrySet(values);
                int sum = sumAsArray(values);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.printf("Calculator: Last sum of values is %s\n", values.values().stream().mapToInt(v -> v).sum());
    }

    /**
     * Tries to calculate sum of values using entry set iterator.
     * Causes ConcurrentModificationException on Collections.synchronizedMap
     * because iterator object is not synchronized
     * Please refer to java.util.Collections.SynchronizedCollection
     *
     * @param values - input values map
     * @return - sum of values
     */
    private static int sumUsingEntrySetIterator(Map<Integer, Integer> values) {
        int sum = 0;
        for (Map.Entry<Integer, Integer> integerIntegerEntry : values.entrySet()) {
            sum += integerIntegerEntry.getValue();
        }
        return sum;
    }

    /**
     * Tries to calculate sum of values using looping for entry set items.
     * Causes ConcurrentModificationException on Collections.synchronizedMap
     * because iterator object is not synchronized
     * Please refer to java.util.Collections.SynchronizedCollection
     *
     * @param values - input values map
     * @return - sum of values
     */
    private static int sumUsingEntrySet(Map<Integer, Integer> values) {
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : values.entrySet()) {
                    sum += entry.getValue();
        }
        return sum;
    }

    /**
     * Tries to calculate sum of values converting values
     * collection to array.
     *
     * @param values - input values map
     * @return - sum of values
     */
    private static int sumAsArray(Map<Integer, Integer> values) {
        int sum = 0;
        for (Object value : values.values().toArray()) {
            sum += (Integer) value;
        }
        return sum;
    }
}
