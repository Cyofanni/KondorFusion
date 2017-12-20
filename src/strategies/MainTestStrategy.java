package strategies;

import runParser.Parser;
import runParser.ParserAbs;
import strategies.basic.*;
import utils.CustomPair;

import java.util.ArrayList;
import java.util.Map;

public class MainTestStrategy {

    public static void main(String[] args){
        ParserAbs pa = new Parser();
        pa.readAndNormalize("/home/marco/Scrivania/test");
        pa.printMap();

        CombANZStrategy canz = new CombANZStrategy();

      /*  Map<Integer, ArrayList<CustomPair<String, Double>>> minMap = CombMINStrategy.combMIN(pa.getLinesHash());
        System.out.println("CombMIN results --------------------");
        CombMINStrategy.printMap(minMap);

        Map<Integer, ArrayList<CustomPair<String, Double>>> maxMap = CombMAXStrategy.combMAX(pa.getLinesHash());
        System.out.println("CombMAX results --------------------");
        CombMINStrategy.printMap(maxMap);

        Map<Integer, ArrayList<CustomPair<String, Double>>> sumMap = CombSUMStrategy.combSUM(pa.getLinesHash());
        System.out.println("CombSUM results --------------------");
        CombMINStrategy.printMap(sumMap);

        Map<Integer, ArrayList<CustomPair<String, Double>>> medMap = CombMEDStrategy.combMED(pa.getLinesHash());
        System.out.println("CombMED results --------------------");
        CombMINStrategy.printMap(medMap);*/

        canz.combANZ(pa.getLinesHash());
       // System.out.println("CombANZ results --------------------");
        //CombMINStrategy.printMap(anzMap);

     /*   Map<Integer, ArrayList<CustomPair<String, Double>>> mnzMap = CombMNZStrategy.combMNZ(pa.getLinesHash());
        System.out.println("CombMNZ results --------------------");
        CombMINStrategy.printMap(mnzMap); */

    }
}
