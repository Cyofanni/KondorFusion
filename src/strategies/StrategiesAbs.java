package strategies;

import utils.Document;
import java.util.ArrayList;

public abstract class StrategiesAbs {

    protected ArrayList<Document> results = new ArrayList<>();


    public static void sort(ArrayList<Document> documents) {
        documents.sort(new CustomComparator()); //sort documents per topic per score
        int rank = 0;
        int oldTop = Integer.MIN_VALUE;

        //set ranks in each document per topic
        for(Document doc : documents){

            if(oldTop != doc.getTopic()){
                rank = 0;
            }
            doc.setRank(rank);
            rank++;
            oldTop = doc.getTopic();
        }
    }

    public void printResults(){
        for (Document doc: results) {
            System.out.println(doc.toString());
        }
    }
}

