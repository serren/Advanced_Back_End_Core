package homework2.task2;

import java.util.Collection;

public abstract class AbstractJob extends Thread {
    protected final Collection<Integer> values;
    public AbstractJob(Collection<Integer> values) {
        this.values = values;
    }

    @SuppressWarnings({"InfiniteLoopStatement", "BusyWait"})
    @Override
    public void run() {
        while (true) {
            try {
                synchronized (this.values) {
                    doJob();
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected abstract void doJob();
}
