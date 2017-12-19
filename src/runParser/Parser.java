package runParser;
import java.util.*;
import java.io.*;
import utils.CustomPair;
import normalizer.Normalizer;


public class Parser extends ParserAbs {
     /**
     * Reads and parses all files containing retrieval results.
     *
     * @param  runDirectory path to directory run file
     * @return LinkedHashMap containing topic and docs id's as key, and scores as values
     */
    @Override
    public void readAndNormalize(String runDirectory){
        Map<KeyForHashing,Double[]> linesHash = new LinkedHashMap<KeyForHashing,Double[]>();
        File[] runFiles = new File(runDirectory).listFiles();


        for(int i = 0; i < runFiles.length; i++){
            Scanner run = null;
            try{
                run = new Scanner(runFiles[i]);
            }
            catch(FileNotFoundException exc){
                exc.printStackTrace();
            }

            Double maxScore = null;
            Double minScore = null;
            int oldTop = Integer.MIN_VALUE;   //old topic, used to check if it changes in the next iteration
            CustomPair<Double, Double> coupleMaxMin = null;
            Map<Integer, CustomPair<Double, Double>> minMaxPerTopic = new LinkedHashMap<Integer, CustomPair<Double, Double>>();  //stores max and min scores for each topic

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
                    minMaxPerTopic.put(oldTop, coupleMaxMin);
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


                if(linesHash.containsKey(currKey)){
                    Double[] temp = linesHash.get(currKey);
                    temp[i] = score;
                    linesHash.put(currKey, temp);
                }
                else{
                    Double[] scores = new Double[runFiles.length];

                    scores[i] = score;
                    linesHash.put(currKey, scores);
                }

                oldTop = top;   //set old topic to current topic
                run.nextLine();  //mandatory instruction
            }


            //add the last (max,min) to the list, because it has been skipped by the while loop
            coupleMaxMin = new CustomPair<>(minScore, maxScore);
            minMaxPerTopic.put(oldTop, coupleMaxMin);

            //throw away the first dummy item from maxMinPerTopic
            minMaxPerTopic.remove(Integer.MIN_VALUE);
            //printMinMaxPerTopic(maxMinPerTopic);
            //normalization
            normalize(i, minMaxPerTopic, linesHash);
        }
        sortPerTopics(linesHash, arrayTopics(runFiles[0].getAbsolutePath()));
    }

    @Override
    protected Double normalizerCaller(Double score, CustomPair<Double,Double> cp){
        Normalizer norm = new Normalizer(score, cp.getFst(), cp.getSnd());
        return norm.normalize();
    }

    @Override
    protected void normalize(int runIndex, Map<Integer, CustomPair<Double, Double>> maxMinCouples,
                             Map<KeyForHashing,Double[]> linesHash) {
        //foreach to normalize everything, through a call to 'normalizerCaller'

        for(Map.Entry<KeyForHashing,Double[]> entry : linesHash.entrySet()){
            KeyForHashing key = entry.getKey();
            Double[] values = entry.getValue();
            int top = key.getTopic();   //current topic from key


            CustomPair<Double,Double> maxMin = maxMinCouples.get(top);
            /*now normalize and replace in 'linesHash' as value for 'key'*/
            values[runIndex] = normalizerCaller(values[runIndex], maxMin);
            linesHash.put(key, values);

        }
    }

    //testing
    public void printMap(){
        for(KeyForHashing key : linesHashSorted.keySet()){
            Double[] values = linesHashSorted.get(key);

            System.out.print("Topic = "+key.getTopic() + ", Document = " + key.getDocument() + ", Scores: ");
            for(int i = 0; i < values.length; i++){
                System.out.print(values[i] + " ");
            }
            System.out.println("");
        }
    }

    //testing
    public void printMinMaxPerTopic(Map<Integer, CustomPair<Double, Double>> maxMinCouples){
        System.out.println("MinMaxPerTopic size: "+maxMinCouples.size());

        for (Integer key: maxMinCouples.keySet()) {
            System.out.println("Topic " + key + " minScore = " + maxMinCouples.get(key).getFst() + " maxScore = " + maxMinCouples.get(key).getSnd());
        }
    }

    //sort linkedHashMap by increasing topics
    //read topics codes yielded by arrayTopics
    private void sortPerTopics(Map<KeyForHashing,Double[]> linesHash, ArrayList<Integer> topics){
        Set<Map.Entry<KeyForHashing, Double[]>> entrySet = linesHash.entrySet();
        ArrayList<Map.Entry<KeyForHashing, Double[]>> listOfEntries = new ArrayList<>(entrySet);


        for(int i = 0; i < topics.size(); i++){

            for(int k = 0; k < listOfEntries.size(); k++){
                Map.Entry<KeyForHashing, Double[]> entry = listOfEntries.get(k);
                KeyForHashing key = entry.getKey();

                if(key.getTopic() == topics.get(i)){
                    linesHashSorted.put(key, entry.getValue());
                    listOfEntries.remove(k);
                }
            }
        }
    }
}