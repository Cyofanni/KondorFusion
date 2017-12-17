package strategies;

import java.util.Comparator;
import utils.CustomPair;

public class CustomComparator implements Comparator<CustomPair<String, Double>> {
    public int compare(CustomPair<String, Double> obj1, CustomPair<String, Double> obj2) {
        Double score1 = obj1.getSnd();
        Double score2 = obj2.getSnd();

        if(score1 < score2)
            return -1;
        if(score1 > score2)
            return 1;
        return 0;
    }

}
