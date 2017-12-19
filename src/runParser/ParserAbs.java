package runParser;
import utils.CustomPair;

import java.util.*;
import java.io.*;



public abstract class ParserAbs {
    protected Map<KeyForHashing,Double[]> linesHash = new LinkedHashMap<KeyForHashing,Double[]>(); //store the lines read from run file

    abstract public void readAndNormalize(String runDirectory);
    abstract protected Double normalizerCaller(Double score, CustomPair<Double,Double> cp);  //should accept a score, and a (max,min) couple

    abstract protected void normalize(int runIndex, Map<Integer, CustomPair<Double, Double>> maxMinCouples); /*interface to the rest of the program*/

    public Map<KeyForHashing,Double[]> getLinesHash() {
        return linesHash;
    }

    abstract public void printMap();

    protected static int countTopics(String runFile){
        File f = new File(runFile);
        Scanner sc = null;
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int count = 0;
        int oldTopic = Integer.MIN_VALUE;

        while(sc.hasNextLine()){
            int topic = sc.nextInt();
            if(oldTopic != topic){
                count++;
                oldTopic = topic;
            }
            sc.nextLine();
        }
        return count;
    }
}
