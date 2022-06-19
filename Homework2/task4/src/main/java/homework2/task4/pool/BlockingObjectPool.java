package homework2.task4.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Pool that block when it has not any items or it full
 */
public class BlockingObjectPool<T> {

    private final BlockingQueue<T> pool;

    /**
     * Creates filled pool of passed size
     *
     * @param size of pool
     */
    public BlockingObjectPool(int size) {
        pool = new LinkedBlockingQueue<>(size);
    }

    /**
     * Gets object from pool or blocks if pool is empty
     *
     * @return object from pool
     */
    public T get() throws InterruptedException {
        return pool.take();
    }

    /**
     * Puts object to pool or blocks if pool is full
     *
     * @param object to be taken back to pool
     */
    public void take(T object) throws InterruptedException {
        pool.put(object);
    }

    /**
     * Returns current count items in the pool
     */
    public int size() {
        return pool.size();
    }
}
