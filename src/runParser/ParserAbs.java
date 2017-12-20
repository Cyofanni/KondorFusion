package runParser;
import utils.CustomPair;

import java.util.*;
import java.io.*;



public abstract class ParserAbs {
    protected Map<KeyForHashing,CustomPair<Integer, Double>[]> linesHashSorted = new LinkedHashMap<>(); //store the lines read from run file

    abstract public void readAndNormalize(String runDirectory);
    abstract protected Double normalizerCaller(Double score, CustomPair<Double,Double> cp);  //should accept a score, and a (max,min) couple

    abstract protected void normalize(int runIndex, Map<Integer, CustomPair<Double, Double>> maxMinCouples,
                                      Map<KeyForHashing,CustomPair<Integer, Double>[]> linesHash); /*interface to the rest of the program*/

    public Map<KeyForHashing,CustomPair<Integer, Double>[]> getLinesHash() {
        return linesHashSorted;
    }

    abstract public void printMap();

    protected static ArrayList<Integer> arrayTopics(String runFile){
        File f = new File(runFile);
        Scanner sc = null;
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

       ArrayList<Integer> topics = new ArrayList<>();
        int oldTopic = Integer.MIN_VALUE;

        while(sc.hasNextLine()){
            int topic = sc.nextInt();
            if(oldTopic != topic){
                topics.add(topic);
                oldTopic = topic;
            }
            sc.nextLine();
        }
        return topics;
    }
}
