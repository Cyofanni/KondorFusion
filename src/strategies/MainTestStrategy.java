package strategies;

import runParser.Parser;
import strategies.basic.*;

public class MainTestStrategy {

    public static void main(String[] args){
        Parser pa = new Parser();
        pa.readAndNormalize("/home/marco/Scrivania/test");
        pa.printRuns();

        System.out.println("CombMIN results --------------------");
        CombMINStrategy cmin = new CombMINStrategy();
        cmin.combMIN(pa.getRuns());
        cmin.printResults();

        System.out.println("CombMAX results --------------------");
        CombMAXStrategy cmax = new CombMAXStrategy();
        cmax.combMAX(pa.getRuns());
        cmax.printResults();

        System.out.println("CombSUM results --------------------");
        CombSUMStrategy csum = new CombSUMStrategy();
        csum.combSUM(pa.getRuns());
        csum.printResults();

        System.out.println("CombMED results --------------------");
        CombMEDStrategy cmed = new CombMEDStrategy();
        cmed.combMED(pa.getRuns());
        cmed.printResults();

        System.out.println("CombANZ results --------------------");
        CombANZStrategy canz = new CombANZStrategy();
        canz.combANZ(pa.getRuns());
        canz.printResults();

        System.out.println("CombMNZ results --------------------");
        CombMNZStrategy cmnz = new CombMNZStrategy();
        cmnz.combMNZ(pa.getRuns());
        cmnz.printResults();

    }
}
