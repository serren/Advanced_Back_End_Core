package homework1.task1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ThreadSafeMap<K,V> extends HashMap<K,V> {
    private final Map<K,V> map;

    public ThreadSafeMap(Map<K,V> map) {
        this.map = map;
    }

    @Override
    public synchronized V get(Object key) {
        synchronized (this) {
            return map.get(key);
        }
    }

    @Override
    public synchronized V put(K key, V value) {
        synchronized (this) {
            return map.put(key, value);
        }
    }

    @Override
    public synchronized Set<Entry<K, V>> entrySet() {
        synchronized (this) {
            return map.entrySet();
        }
    }
}
