package runParser;
import utils.CustomPair;

import java.util.*;
import java.io.*;



public abstract class ParserAbs {
    protected LinkedHashMap<KeyForHashing,Double[]> linesHash = new LinkedHashMap<KeyForHashing,Double[]>(); //store the lines read from run file

    abstract public void readAndNormalize(String runDirectory);
    abstract protected Double normalizerCaller(Double score, CustomPair<Double,Double> cp);  //should accept a score, and a (max,min) couple

    abstract protected void normalize(int runIndex, List<CustomPair<Double, Double>> maxMinCouples); /*interface to the rest of the program*/

    public LinkedHashMap<KeyForHashing,Double[]> getLinesHash() {
        return linesHash;
    }

    abstract public void printMap();
}
