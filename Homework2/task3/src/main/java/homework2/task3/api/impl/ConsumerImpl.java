package homework2.task3.api.impl;

import homework2.task3.api.Consumer;

import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

public class ConsumerImpl implements Consumer, Runnable {

    private final String topic;
    private final Map<String, Queue<String>> messageBus;

    public ConsumerImpl(String topic, Map<String, Queue<String>> messageBus) {
        this.topic = topic;
        this.messageBus = messageBus;
    }

    @Override
    public void onNewMessage(String topic) {
        consume(topic);
    }

    private void consume(String topic) {
        synchronized (messageBus) {
            if (messageBus.containsKey(topic)) {
                synchronized (messageBus) {
                    Queue<String> queue = messageBus.get(topic);
                    String message = queue.poll();
                    if (message != null) {
                        System.out.println(Thread.currentThread() + " received a new message " + message);
                    }
                }
            }
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            consume(this.topic);
            Thread.yield();
        }
    }
}
