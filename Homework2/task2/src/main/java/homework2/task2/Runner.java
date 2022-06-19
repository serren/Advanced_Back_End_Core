package homework2.task2;

import java.util.ArrayList;
import java.util.Collection;

public class Runner {

    public static void main(String[] args) throws InterruptedException {
        runTest(new ArrayList<>());
    }

    private static void runTest(Collection<Integer> values) throws InterruptedException {
        Thread producer = new Producer(values);
        Thread calculator = new Calculator(values);
        Thread squareCalculator = new SquareCalculator(values);

        calculator.start();
        squareCalculator.start();
        producer.start();

        calculator.join();
        squareCalculator.join();
        producer.join();
    }
}
