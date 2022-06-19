package homework1.task1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Runner {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("----------- Testing HashMap ---------------");
        runTest(new HashMap<>());

        System.out.println("----------- Testing ConcurrentHashMap ---------------");
        runTest(new ConcurrentHashMap<>());

        System.out.println("----------- Testing Collections.synchronizedMap ---------------");
        runTest(Collections.synchronizedMap(new HashMap<>()));

        // System.out.println("----------- Testing custom ThreadSafeMap ---------------");
        // runTest(new ThreadSafeMap<Integer, Integer>(new HashMap<Integer, Integer>()));
    }

    private static void runTest(Map<Integer, Integer> values) throws InterruptedException {
        Thread producer = new Producer(values);
        Thread calculator = new Calculator(values);
        calculator.start();
        producer.start();
        calculator.join();
        producer.join();
    }
}
