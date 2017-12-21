package runParser;
import utils.Topic;

import java.util.*;


public abstract class ParserAbs {

    List<Topic> runs = new ArrayList<>();

    abstract public void readAndNormalize(String runDirectory);

    public List<Topic> getRuns(){
        return runs;
    }
}
