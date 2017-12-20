package strategies;

import utils.CustomPair;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class StrategiesAbs {
    public static void sort(Map<Integer, ArrayList<CustomPair<String, Double>>> map) {
        for (Integer key: map.keySet()) {
            map.get(key).sort(new CustomComparator());
        }
    }

    protected Map<Integer, ArrayList<CustomPair<String, Double>>> results = new LinkedHashMap<>();


    public static void printMap(Map<Integer, ArrayList<CustomPair<String, Double>>> m){

        for(Integer topic : m.keySet()){
            ArrayList<CustomPair<String, Double>> docScores = m.get(topic);

            for (CustomPair<String, Double> c: docScores) {
                System.out.println("Topic:" + topic + " document:" + c.getFst() + " score:" + c.getSnd());
            }
        }

    }
}

