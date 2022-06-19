package homework2.task3;

import homework2.task3.api.impl.ConsumerImpl;
import homework2.task3.api.impl.ProduceImpl;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class Runner {

    public static void main (String[] args) throws InterruptedException {
        Map<String, Queue<String>> messageBus = new ConcurrentHashMap<>();
        int iterationCount = 2;
        Thread producer1 = new Thread(new ProduceImpl("uuid", messageBus, iterationCount), "ProducerThread-1");
        Thread producer2 = new Thread(new ProduceImpl("uuid", messageBus, iterationCount), "ProducerThread-2");
        Thread consumer1 = new Thread(new ConsumerImpl("uuid", messageBus), "ConsumerThread-1");
        Thread consumer2 = new Thread(new ConsumerImpl("uuid", messageBus), "ConsumerThread-2");

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        producer1.join();
        producer2.join();
        consumer1.join();
        consumer2.join();
    }
}
