package strategies.basic;

import runParser.KeyForHashing;
import strategies.StrategiesAbs;
import utils.CustomPair;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class CombANZStrategy extends StrategiesAbs {

//    public void combANZ(Map<KeyForHashing,CustomPair<Integer,Double>[]> linesHash) {
//        for (Map.Entry<KeyForHashing, CustomPair<Integer,Double>[]> entry : linesHash.entrySet()) {
//            KeyForHashing key = entry.getKey();
//            CustomPair<Integer,Double>[] values = entry.getValue();
//            Double[] scores = new Double[values.length];
//            for(int i = 0; i < values.length; i++){
//                CustomPair<Integer,Double> cp = values[i];
//                scores[i] = cp.getSnd();
//            }
//            int top = key.getTopic();   //current topic from key
//            String doc = key.getDocument(); //current document
//
//            double sum = 0;
//            int numberOfZeroSimilarities = 0;
//
//            for (int i = 0; i < values.length; i++) {
//                if(scores[i] == null){
//                    numberOfZeroSimilarities++;
//                    continue;
//                }
//                sum += scores[i];
//            }
//
//            double score = sum / (values.length - numberOfZeroSimilarities); //mean of values
//
//            CustomPair<String, Double> docScore = new CustomPair<>(doc, score); //couple document and final score
//            ArrayList<CustomPair<String, Double>> documents;
//
//            if (!results.containsKey(top)) {
//                documents = new ArrayList<>();
//                results.put(top, documents);
//            } else {
//                documents = results.get(top);
//            }
//
//            documents.add(docScore);
//
//        }
//        sort(results);
//    }
}
