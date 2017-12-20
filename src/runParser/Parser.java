package runParser;
import java.util.*;
import java.io.*;
import utils.CustomPair;
import normalizer.Normalizer;
import utils.Topic;
import utils.Value;


public class Parser extends ParserAbs {
     /**
     * Reads and parses all files containing retrieval results.
     *
     * @param  runDirectory path to directory run file
     * @return LinkedHashMap containing topic and docs id's as key, and scores as values
     */
    @Override
    public void readAndNormalize(String runDirectory){

        File[] runFiles = new File(runDirectory).listFiles();

        for(int runIndex = 0; runIndex < runFiles.length; runIndex++){
            int topicIndex = -1;
            Scanner run = null;
            try{
                run = new Scanner(runFiles[runIndex]);
            }
            catch(FileNotFoundException exc){
                exc.printStackTrace();
            }

            Double maxScore = null;
            Double minScore = null;
            int oldTop = Integer.MIN_VALUE;   //old topic, used to check if it changes in the next iteration
            CustomPair<Double, Double> coupleMaxMin = null;

            Map<String, Value[]> documentMap = null;

            while(run.hasNextLine()){
                int top;   //topic
                String doc;  //document id
                Double score;  //score
                top = run.nextInt();
                run.next();
                doc = run.next();
                int rank = run.nextInt();
                score = run.nextDouble();
                run.nextLine(); //mandatory instruction


                if(oldTop != top){
                    topicIndex++;
                    oldTop = top;
                    if(runIndex == 0){ //scan only the first run and insert the topics
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


            }
        }



    }

    //@Override
    protected Double normalizerCaller(Double score, CustomPair<Double,Double> cp){
        Normalizer norm = new Normalizer(score, cp.getFst(), cp.getSnd());
        return norm.normalize();
    }

    //@Override
    protected void normalize() {
        //foreach to normalize everything, through a call to 'normalizerCaller'

    }

    //testing
    public void printRuns(){
        for (int i = 0; i < runs.size(); i++ ) {
            System.out.println(runs.get(i));
        }
    }

    //testing
    public void printMinMaxPerTopic(Map<Integer, CustomPair<Double, Double>> maxMinCouples){
        System.out.println("MinMaxPerTopic size: "+maxMinCouples.size());

        for (Integer key: maxMinCouples.keySet()) {
            System.out.println("Topic " + key + " minScore = " + maxMinCouples.get(key).getFst() + " maxScore = " + maxMinCouples.get(key).getSnd());
        }
    }


}