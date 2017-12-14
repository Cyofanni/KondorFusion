package runParser;
import java.util.*;
import java.io.*;

public class Parser implements ParserI{
    public HashMap<KeyForHashing, Double> readRun(String runFile){
        HashMap<KeyForHashing, Double> linesHash = new HashMap<>();
        Scanner run = null;
        try{
            run = new Scanner(new File(runFile));
        }
        catch(FileNotFoundException exc){}

        while(run.hasNextLine()){
            int top;   //topic
            String doc;  //document id
            Double score;  //score
            top = run.nextInt();
            run.next();
            doc = run.next();
            run.next();
            score = run.nextDouble();

            KeyForHashing currKey = new KeyForHashing(top, doc);
            linesHash.put(currKey, score);


        }






        return linesHash;
    }
}
