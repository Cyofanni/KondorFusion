package runParser;
import java.util.*;
import java.io.*;


public interface ParserI {
    public HashMap<KeyForHashing, Double> readRun(String runFile);
    // public void normalize(): should accept a score, and a (max,min) couple
}
