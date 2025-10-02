package core.basesyntax.db;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private final Map<String, Integer> storage;

    public Storage() {
        storage = new HashMap<>();
    }

    public Integer get(String key) {
        try {
            return storage.get(key);
        } catch (NullPointerException e) {
            return -1;
        }
    }

    public void set(String key, int value) {
        storage.put(key, value);
    }

    public void put(String key, int value) {
        storage.put(key, value);
    }

    public Map<String, Integer> getStorage() {
        return Map.copyOf(storage);
    }

    public void clear() {
        storage.clear();
    }

    public boolean contains(String key) {
        return storage.containsKey(key);
    }
}
