package homework2.task3.api.impl;

import homework2.task3.api.Consumer;
import homework2.task3.api.Producer;

import java.util.*;

public class ProduceImpl implements Producer, Runnable {

    Collection<Consumer> consumers = new HashSet<>();
    private final Map<String, Queue<String>> messageBus;
    private final String topic;
    private final int iterationCount;

    public ProduceImpl(String topic, Map<String, Queue<String>> messageBus, int iterationCount) {
        this.topic = topic;
        this.messageBus = messageBus;
        this.iterationCount = iterationCount;
    }

    @Override
    public void newMessage(String topic, String message) {
        synchronized (messageBus) {
            messageBus.computeIfAbsent(topic, t -> new LinkedList<>()).offer(message);
            System.out.println(Thread.currentThread() + " send a new message " + message);
            consumers.forEach(c -> c.onNewMessage(topic));
        }
    }

    @Override
    public void subscribe(Consumer consumer) {
        consumers.add(consumer);
    }

    @Override
    public void unsubscribe(Consumer consumer) {
        consumers.remove(consumer);
    }

    @Override
    public void run() {
        int i = 0;
        while(i <= iterationCount) {
            try {
                newMessage(topic, UUID.randomUUID().toString());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ++i;
        }
    }
}
