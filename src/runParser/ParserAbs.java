package runParser;
import utils.CustomPair;

import java.util.*;
import java.io.*;



public abstract class ParserAbs {
    protected LinkedHashMap<KeyForHashing,Double[]> linesHash = new LinkedHashMap<KeyForHashing,Double[]>(); //store the lines read from run file
    protected List<CustomPair<Double, Double>> maxMinPerTopic = new ArrayList();  //stores max and min scores for each topi

    abstract public void readAndNormalize(String runDirectory);
    abstract protected Double normalizerCaller(Double score, CustomPair<Double,Double> cp);  //should accept a score, and a (max,min) couple

    abstract protected void normalize(int runIndex); /*interface to the rest of the program*/

    public LinkedHashMap<KeyForHashing,Double[]> getLinesHash() {
        return linesHash;
    }

    public List<CustomPair<Double, Double>> getMaxMinPerTopic() {
        return maxMinPerTopic;
    }

    abstract public void printMap();
}
