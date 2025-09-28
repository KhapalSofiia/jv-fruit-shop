package core.basesyntax.service;

import java.util.Map;

public interface ProductCounterService {
    public Map<String, Integer> countTheProducts(String[][] report);
}
