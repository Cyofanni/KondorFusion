package runParser;
import java.util.*;
import java.io.*;
import utils.CustomPair;
import normalizer.Normalizer;


public class Parser extends ParserAbs {
    /**
     * Reads and parses a file containing retrieval results.
     *
     * @param  runFile  path to run file
     * @return LinkedHashMap containing topic and docs id's as key, and scores as values
     */
    @Override
    protected void readRun(String runFile){
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
            run.nextLine();  //mandatory instruction
        }

        //add the last (max,min) to the list, because it has been skipped by the while loop
        coupleMaxMin = new CustomPair<>(minScore, maxScore);
        maxMinPerTopic.add(coupleMaxMin);

        //throw away the first dummy item from maxMinPerTopic
        maxMinPerTopic.remove(0);
    }

    @Override
    protected Double normalizerCaller(Double score, CustomPair<Double,Double> cp){
        Normalizer norm = new Normalizer(score, cp.getFst(), cp.getSnd());
        return norm.normalize();
    }

    @Override
    public void readAndNormalize(String runFile){
        readRun(runFile);  //this call sets 'linesHash' and 'maxMinPerTopic' to the non-normalized values
        //foreach to normalize everything, through a call to 'normalizerCaller'
        int topicCursor = -1;    //index for topics, should run over 'maxMinPerTopic'
        int oldTop = Integer.MIN_VALUE;   //old topic, used to check if it changes in the next iteration

        for(Map.Entry<KeyForHashing,Double> entry : linesHash.entrySet()){
            KeyForHashing key = entry.getKey();
            Double value = entry.getValue();
            int top = key.getTopic();   //current topic from key
            /*should still detect when the topic changes*/
            if (top != oldTop){
                topicCursor++;
            }

            CustomPair<Double,Double> maxMin = maxMinPerTopic.get(topicCursor);
            /*now normalize and replace in 'linesHash' as value for 'key'*/
            Double normalizedScore = normalizerCaller(value, maxMin);
            linesHash.put(key, normalizedScore);

            oldTop = top;
        }
    }
}