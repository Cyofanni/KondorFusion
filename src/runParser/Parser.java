package runParser;

import java.util.*;
import java.io.*;
import normalizer.Normalizer;
import utils.Topic;
import utils.Value;


public class Parser extends ParserAbs {
     /**
     * Reads and parses all files containing retrieval results.
     *
     * @param  runDirectory path to directory run file
     */
    @Override
    public void readAndNormalize(String runDirectory){

        File[] runFiles = new File(runDirectory).listFiles();

        for(int runIndex = 0; runIndex < runFiles.length; runIndex++){ //cycle on runs
            int topicIndex = -1;
            Scanner run = null;
            try{
                run = new Scanner(runFiles[runIndex]);
            }
            catch(FileNotFoundException exc){
                exc.printStackTrace();
            }

            Double maxScore = Double.NEGATIVE_INFINITY; //max score per topic per run, used to compute normalization
            Double minScore = Double.POSITIVE_INFINITY; //min score per topic per run, used to compute normalization
            int oldTop = Integer.MIN_VALUE; //old topic, used to check if it changes in the next iteration

            Map<String, Value[]> documentMap = null;

            while(run.hasNextLine()){ //cycle on each row of run file
                //run format: topic, Q0, documentID, documentRank, documentScore, runName
                int top;   //topic
                String doc;  //documentID
                int rank;  //documentRank
                Double score;  //documentScore

                //collect information from run file
                top = run.nextInt();
                run.next();
                doc = run.next();
                rank = run.nextInt();
                score = run.nextDouble();
                run.nextLine(); //mandatory instruction: scanner must start from next line

                if(oldTop != top){ //normalization is performed on the previous topic when a new one occurs
                    if(oldTop != Integer.MIN_VALUE){ //first topic of the run must wait
                        //normalize
                        normalize(documentMap, runIndex, minScore, maxScore);
                        //reset min score and max score
                        maxScore = Double.NEGATIVE_INFINITY;
                        minScore = Double.POSITIVE_INFINITY;
                    }
                    topicIndex++; //topics are disposed in increasing order
                    oldTop = top;
                    if(runIndex == 0){ //insert elements in results only during the first run
                        documentMap = new LinkedHashMap<>();
                        Topic t = new Topic(top, documentMap);
                        runs.add(t);
                    }
                    else{
                        documentMap = runs.get(topicIndex).getMap();
                    }
                }

                Value[] v = null;
                if(documentMap.containsKey(doc)){
                    v = documentMap.get(doc);
                }
                else{
                    v = new Value[runFiles.length];
                    for(int k = 0; k < v.length; k++){
                        v[k] = new Value(null, null);
                    }
                    documentMap.put(doc, v);
                }
                v[runIndex] = new Value(rank, score);

                if (score > maxScore){
                    maxScore = score;
                }
                else if (score < minScore){
                    minScore = score;
                }
            }

            //normalize the last topic of the run
            normalize(documentMap, runIndex, minScore, maxScore);
        }
    }

    //@Override
    protected Double normalizerCaller(Double score, Double minScore, Double maxScore){
        Normalizer norm = new Normalizer(score, minScore, maxScore);
        return norm.normalize();
    }

    //@Override
    protected void normalize(Map<String, Value[]> documentMap, int runIndex, Double minScore, Double maxScore) {
        //foreach to normalize everything, through a call to 'normalizerCaller'
        for (String doc: documentMap.keySet()) {
            Value[] v = documentMap.get(doc);
            Double normalizeScore = normalizerCaller(v[runIndex].getScore(), minScore, maxScore);
            v[runIndex].setScore(normalizeScore);
        }
    }

    //testing
    public void printRuns(){
        for (int i = 0; i < runs.size(); i++ ) {
            System.out.println(runs.get(i));
        }
    }
}