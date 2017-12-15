package runParser;
import utils.CustomPair;

import java.util.*;
import java.io.*;



public abstract class ParserAbs {
    abstract protected HashMap<KeyForHashing, Double> readRun(String runFile);
    abstract protected Double normalizerCaller(Double score, CustomPair<Double,Double> cp);  //should accept a score, and a (max,min) couple
}
