package runParser;
import utils.CustomPair;
import utils.Topic;

import java.util.*;
import java.io.*;



public abstract class ParserAbs {

    List<Topic> runs = new ArrayList<>();

    abstract public void readAndNormalize(String runDirectory);


}
