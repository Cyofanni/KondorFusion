package runParser;

public class KeyForHashing{
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

    //override hashCode method (important for hashing)
    public int hashCode(){
        return topic + document.hashCode();
    }

    //override equals method (important for hashing)
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        KeyForHashing k = (KeyForHashing) obj;

        if (document == null) {
            if (k.document != null){
                return false;
            }
        }
        else if(!document.equals(k.document)){
            return false;
        }

        if(topic != k.topic){
            return false;
        }
        return true;
    }
}