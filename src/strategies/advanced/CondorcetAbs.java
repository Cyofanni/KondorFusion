package strategies.advanced;

import utils.Document;
import utils.Topic;
import java.util.ArrayList;
import java.util.List;

public abstract class CondorcetAbs {
    protected ArrayList<Document> results = new ArrayList<>();
    protected String runId;

    public abstract void condorcetFuse(List<Topic> runs);

    public void printResults(){
        for (Document doc: results) {
            System.out.println(doc.toString() + " " + runId);
        }
    }

    protected void computeRanks(ArrayList<Document> docs) {
        int rank = 0;
        int oldTop = Integer.MIN_VALUE;

        //set ranks in each document per topic
        for(Document doc : docs){

            if(oldTop != doc.getTopic()){
                rank = 0;
            }
            doc.setRank(rank);
            rank++;
            oldTop = doc.getTopic();
        }
    }
}

