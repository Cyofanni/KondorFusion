package strategies;

import java.util.Comparator;

import utils.Document;

public class CustomComparator implements Comparator<Document> {
    public int compare(Document obj1, Document obj2) {
        Double score1 = obj1.getScore();
        Double score2 = obj2.getScore();

        Integer top1 = obj1.getTopic();
        Integer top2 = obj2.getTopic();

        if(top1 < top2){
            return -1;
        }
        else if(top1 > top2){
            return 1;
        }

        if(score1 < score2){
            return 1;
        }
        if(score1 > score2){
            return -1;
        }
        return 0;

    }

}
