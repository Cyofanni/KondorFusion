package runParser;
import java.util.*;
import java.io.*;

class KeyForHashing{
    private int topic;
    private String document;
    public KeyForHashing(int t, String d){
        topic = t;
        document = d;
    }
}

public interface ParserI {
    public HashMap<KeyForHashing, Double> readRun(String runFile);
    // public void normalize(): should accept a score, and a (max,min) couple
}
