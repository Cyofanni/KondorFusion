package utils;

import java.util.Map;

public class Topic {

    private int topic;
    private Map<String, Value[]> map;

    public Topic(int topic, Map<String, Value[]> map) {
        this.topic = topic;
        this.map = map;
    }

    public int getTopic() {
        return topic;
    }

    public void setTopic(int topic) {
        this.topic = topic;
    }

    public Map<String, Value[]> getMap() {
        return map;
    }

    public void setMap(Map<String, Value[]> map) {
        this.map = map;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();

        for(String docId : map.keySet()){
            s.append("Topic " + topic + "docID: " + docId);
            for(Value v : map.get(docId)){
                s.append(" " + v.toString());
            }
            s.append("\n");
        }
        return s.toString();
    }
}
