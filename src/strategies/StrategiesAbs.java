package strategies;

import utils.CustomPair;
import utils.Document;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class StrategiesAbs {

    protected ArrayList<Document> results = new ArrayList<>();


    public static void sort(ArrayList<Document> documents) {
        documents.sort(new CustomComparator());
        int rank = 0;
        int oldTop = Integer.MIN_VALUE;

        //TODO da sistemare rank!!!!!!!!!!!!!!!!!
        for(Document doc : documents){
            doc.setRank(rank);
            if(oldTop != doc.getTopic() && oldTop != Integer.MIN_VALUE){
                rank = 0;
                oldTop = doc.getTopic();
            }
            rank++;
        }
    }

    public void printResults(){
        for (Document doc: results) {
            System.out.println(doc.toString());
        }
    }

//    public static void printMap(Map<Integer, ArrayList<CustomPair<String, Double>>> m){
//
//        for(Integer topic : m.keySet()){
//            ArrayList<CustomPair<String, Double>> docScores = m.get(topic);
//
//            for (CustomPair<String, Double> c: docScores) {
//                System.out.println("Topic:" + topic + " document:" + c.getFst() + " score:" + c.getSnd());
//            }
//        }
//
//    }
}

