package homework1.task1;

import java.util.Map;

public class Producer extends Thread {

    private final Map<Integer, Integer> values;

    public Producer(Map<Integer, Integer> values) {
         this.values = values;
    }

    @Override
    public void run() {
        for(var i = 0; i < 100; i++) {
            try {
                values.put(i, i + 10);
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Producer: Finished");
    }
}
