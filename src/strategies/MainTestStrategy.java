package strategies;

import runParser.KeyForHashing;
import runParser.Parser;
import runParser.ParserAbs;
import utils.CustomPair;

import java.util.ArrayList;
import java.util.Map;

public class MainTestStrategy {

    public static void main(String[] args){
        ParserAbs pa = new Parser();
        pa.readAndNormalize("/home/marco/Scrivania/test");
        pa.printMap();

        Map<Integer, ArrayList<CustomPair<String, Double>>> r = CombMINStrategy.combMIN(pa.getLinesHash());

        for(Integer topic : r.keySet()){
            ArrayList<CustomPair<String, Double>> docScores = r.get(topic);

            for (CustomPair<String, Double> c: docScores) {
                System.out.println("Topic:" + topic + " document:" + c.getFst() + " score:" + c.getSnd());
            }
        }
    }
}
