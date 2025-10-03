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
            throw new IllegalArgumentException("Storage key not found"
                    + key, e);
        }
    }

    public void set(String key, int value) {
        if (key == null) {
            throw new IllegalArgumentException("Storage key can't be null");
        }
        if (key.isBlank()) {
            throw new IllegalArgumentException("Storage key can't be blank");
        }
        storage.put(key, value);
    }

    public void put(String key, int value) {
        if (key == null) {
            throw new IllegalArgumentException("Storage key can't be null");
        }
        if (key.isBlank()) {
            throw new IllegalArgumentException("Storage key can't be blank");
        }
        storage.put(key, value);
    }

    public Map<String, Integer> getStorage() {
        return Map.copyOf(storage);
    }

    public void clear() {
        storage.clear();
    }

    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Storage key can't be null");
        }
        if (key.isBlank()) {
            throw new IllegalArgumentException("Storage key can't be blank");
        }
        return storage.containsKey(key);
    }
}
