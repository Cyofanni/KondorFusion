package runParser;

class KeyForHashing{
    private int topic;
    private String document;
    public KeyForHashing(int t, String d){
        topic = t;
        document = d;
    }

    public int getTopic(){
        return topic;
    }

    public String getDocument(){
        return document;
    }
}