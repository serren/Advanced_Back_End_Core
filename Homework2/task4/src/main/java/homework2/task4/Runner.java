package homework2.task4;

import homework2.task4.pool.BlockingObjectPool;

import java.util.UUID;

@SuppressWarnings("BusyWait")
public class Runner {

    public static void main(String[] args) throws InterruptedException {

        final BlockingObjectPool<String> pool = new BlockingObjectPool<>(2);

        Thread producer = new Thread(
                () -> {
                    while(true) {
                        try {
                            String message = UUID.randomUUID().toString();
                            pool.take(message);
                            System.out.println(Thread.currentThread() + " put " + message);
                            // System.out.println(Thread.currentThread() + " pool size " + pool.size());
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                "ProducerThread-1"
        );

        Thread consumer = new Thread(
                () -> {
                    while(true) {
                        try {
                            String message = pool.get();
                            System.out.println(Thread.currentThread() + " got " + message);
                            // System.out.println(Thread.currentThread() + " pool size " + pool.size());
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                "ConsumerThread-1"
        );

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}