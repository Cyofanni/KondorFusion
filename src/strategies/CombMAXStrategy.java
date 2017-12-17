package strategies;

import runParser.KeyForHashing;
import utils.CustomPair;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class CombMAXStrategy {

    public static Map<Integer, ArrayList<CustomPair<String, Double>>> combMAX(Map<KeyForHashing,Double[]> linesHash){

        Map<Integer, ArrayList<CustomPair<String, Double>>> results = new LinkedHashMap<>();

        for(Map.Entry<KeyForHashing,Double[]> entry : linesHash.entrySet()){
            KeyForHashing key = entry.getKey();
            Double[] values = entry.getValue();
            int top = key.getTopic();   //current topic from key
            String doc = key.getDocument(); //current document

            double max = Double.MIN_VALUE;

            for(int i = 0; i < values.length; i++){
                if(values[i] > max){
                    max = values[i];
                }
            }

            CustomPair<String, Double> docScore = new CustomPair<>(doc, max); //couple document and final score
            ArrayList<CustomPair<String, Double>> documents;

            if(!results.containsKey(top)){
                documents = new ArrayList<>();
                results.put(top, documents);
            }
            else{
                documents = results.get(top);
            }

            documents.add(docScore);

        }
        return null;
    }
}
