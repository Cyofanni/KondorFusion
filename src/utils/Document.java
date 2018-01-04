package utils;

public class Document extends Value{

    private Integer topic;
    private String docId;

    public Document(Integer topic, String document, Integer rank, Double score) {
        super(rank, score);
        docId = document;
        this.topic = topic;
    }

    public Integer getTopic() {
        return topic;
    }

    public String getDocId() {
        return docId;
    }

    public String toString(){
        return topic + " " + docId + " " + rank + " " + score;
    }
}
