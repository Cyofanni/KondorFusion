package strategies.basic;

import runParser.KeyForHashing;
import strategies.StrategiesAbs;
import utils.CustomPair;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class CombMNZStrategy extends StrategiesAbs {

    public static Map<Integer, ArrayList<CustomPair<String, Double>>> combMNZ(Map<KeyForHashing,Double[]> linesHash) {

        Map<Integer, ArrayList<CustomPair<String, Double>>> results = new LinkedHashMap<>();

        for (Map.Entry<KeyForHashing, Double[]> entry : linesHash.entrySet()) {
            KeyForHashing key = entry.getKey();
            Double[] values = entry.getValue();
            int top = key.getTopic();   //current topic from key
            String doc = key.getDocument(); //current document

            double sum = 0;
            int numberOfZeroSimilarities = 0;

            for (int i = 0; i < values.length; i++) {
                if(values[i] == null){
                    numberOfZeroSimilarities++;
                    continue;
                }
                sum += values[i];
            }

            double score = sum * (values.length - numberOfZeroSimilarities); //mean of values

            CustomPair<String, Double> docScore = new CustomPair<>(doc, score); //couple document and final score
            ArrayList<CustomPair<String, Double>> documents;

            if (!results.containsKey(top)) {
                documents = new ArrayList<>();
                results.put(top, documents);
            } else {
                documents = results.get(top);
            }

            documents.add(docScore);

        }
        sort(results);

        return results;
    }
}
