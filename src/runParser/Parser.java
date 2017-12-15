package runParser;
import java.util.*;
import java.io.*;
import utils.CustomPair;


public class Parser extends ParserAbs {
    private List<CustomPair<Double, Double>> maxMinPerTopic = new ArrayList();  //stores max and min scores for each topic

    /**
     * Reads and parses a file containing retrieval results.
     *
     * @param  runFile  path to run file
     * @return HashMap containing topic and docs id's as key, and scores as values
     */
    protected HashMap<KeyForHashing, Double> readRun(String runFile){
        HashMap<KeyForHashing,Double> linesHash = new HashMap<KeyForHashing,Double>();
        Scanner run = null;
        try{
            run = new Scanner(new File(runFile));
        }
        catch(FileNotFoundException exc){}

        Double maxScore = null;
        Double minScore = null;
        int oldTop = Integer.MIN_VALUE;   //old topic, used to check if it changes in the next iteration
        CustomPair<Double, Double> coupleMaxMin = null;

        while(run.hasNextLine()){
            int top;   //topic
            String doc;  //document id
            Double score;  //score
            top = run.nextInt();
            run.next();
            doc = run.next();
            run.next();
            score = run.nextDouble();

            if (top != oldTop){
                /**if the topic changes, put (max,min) of the previous topic in maxMinPerTopic and reset;
                   the topic changes trivially also in the first iteration, so put a dummy couple in the
                   first item of the list and throw it away later
                 */
                coupleMaxMin = new CustomPair<Double,Double>(minScore, maxScore);
                maxMinPerTopic.add(coupleMaxMin);
                maxScore = Double.NEGATIVE_INFINITY;   //reset for the next topic
                minScore = Double.POSITIVE_INFINITY;
            }

            if (score > maxScore){
                maxScore = score;
            }
            else if (score < minScore){
                minScore = score;
            }

            KeyForHashing currKey = new KeyForHashing(top, doc);
            linesHash.put(currKey, score);

            oldTop = top;   //set old topic to current topic
        }

        //add the last (max,min) to the list, because it has been skipped by the while loop
        coupleMaxMin = new CustomPair<>(minScore, maxScore);
        maxMinPerTopic.add(coupleMaxMin);

        //throw away the first dummy item from maxMinPerTopic
        maxMinPerTopic.remove(0);

        return linesHash;
    }

    protected Double normalizerCaller(Double score, CustomPair<Double,Double> cp){
        return 0d;     //dummy return
    }
}