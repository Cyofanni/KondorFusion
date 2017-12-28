package strategies.advanced;

//This class simplifies the condorcetFuse() method of class Condorcet
public class CondorcetValue {
    int topic;
    String document;
    Integer[] ranks;

    CondorcetValue(int top, String docId, Integer[] values){
        topic = top;
        document = docId;
        ranks = values;
    }

    public Integer[] getRanks() {
        return ranks;
    }

    public String getDocument() {
        return document;
    }

    public int getTopic() {
        return topic;
    }
}
