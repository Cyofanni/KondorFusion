package strategies;

import utils.CustomPair;

import java.util.Comparator;
import java.util.List;

public class StrategiesAbs {
    public List<CustomPair<String, Double>> sort(List<CustomPair<String, Double>> docScores) {

        docScores.sort(new CustomComparator());
    }
}

