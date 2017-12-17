package strategies;

import utils.CustomPair;
import java.util.List;

public abstract class StrategiesAbs {
    public void sort(List<CustomPair<String, Double>> docScores) {
        docScores.sort(new CustomComparator());
    }
}

