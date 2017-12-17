package runParser;

class KeyForHashing{
    private int topic;
    private String document;
    public KeyForHashing(int t, String d){
        topic = t;
        document = d;
    }

    public KeyForHashing() {
        topic = Integer.MIN_VALUE;
        document = null;
    }

    public int getTopic(){
        return topic;
    }

    public String getDocument(){
        return document;
    }

    public void setTopic(int t) {topic = t;}

    public void setDocument(String doc) {document = doc;}

    public int hashCode(){
        return topic + document.hashCode();
    }
}