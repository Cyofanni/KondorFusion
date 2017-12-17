package strategies;

import utils.CustomPair;
import java.util.ArrayList;
import java.util.Map;

public abstract class StrategiesAbs {
    public void sort(Map<Integer, ArrayList<CustomPair<String, Double>>> map) {
        for (Integer key: map.keySet()) {
            map.get(key).sort(new CustomComparator());
        }
    }
}

