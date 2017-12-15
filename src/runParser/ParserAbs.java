package runParser;
import utils.CustomPair;

import java.util.*;
import java.io.*;



public abstract class ParserAbs {
    protected HashMap<KeyForHashing,Double> linesHash = new HashMap<KeyForHashing,Double>(); //store the lines read from run file
    protected List<CustomPair<Double, Double>> maxMinPerTopic = new ArrayList();  //stores max and min scores for each topi

    abstract protected void readRun(String runFile);
    abstract protected Double normalizerCaller(Double score, CustomPair<Double,Double> cp);  //should accept a score, and a (max,min) couple

    abstract public void readAndNormalize(String runFile); /*interface to the rest of the program*/

    public HashMap<KeyForHashing, Double> getLinesHash() {
        return linesHash;
    }

    public List<CustomPair<Double, Double>> getMaxMinPerTopic() {
        return maxMinPerTopic;
    }
}
