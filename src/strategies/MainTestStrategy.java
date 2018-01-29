package strategies;

import runParser.Parser;
import strategies.basic.*;

import java.io.FileWriter;
import java.io.IOException;

public class MainTestStrategy {

    public static void main(String[] args){
        Parser pa = new Parser();
        pa.readAndNormalize("/home/marco/Scrivania/run");
        //pa.printRuns();

        String pathDest = "/home/marco/Scrivania/runFuse/fusione10run/10";
        FileWriter f = null;


        CombMINStrategy cmin = new CombMINStrategy();
        cmin.combMIN(pa.getRuns());
        try {
            f = new FileWriter(pathDest + "/CombMIN");
            f.write(cmin.toString());
            f.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CombMAXStrategy cmax = new CombMAXStrategy();
        cmax.combMAX(pa.getRuns());
        try {
            f = new FileWriter(pathDest + "/CombMAX");
            f.write(cmax.toString());
            f.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CombSUMStrategy csum = new CombSUMStrategy();
        csum.combSUM(pa.getRuns());
        try {
            f = new FileWriter(pathDest + "/CombSUM");
            f.write(csum.toString());
            f.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CombMEDStrategy cmed = new CombMEDStrategy();
        cmed.combMED(pa.getRuns());
        try {
            f = new FileWriter(pathDest + "/CombMED");
            f.write(cmed.toString());
            f.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CombANZStrategy canz = new CombANZStrategy();
        canz.combANZ(pa.getRuns());
        try {
            f = new FileWriter(pathDest + "/CombANZ");
            f.write(canz.toString());
            f.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CombMNZStrategy cmnz = new CombMNZStrategy();
        cmnz.combMNZ(pa.getRuns());
        try {
            f = new FileWriter(pathDest + "/CombMNZ");
            f.write(cmnz.toString());
            f.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        //System.out.println("CombMIN results --------------------");
//        CombMINStrategy cmin = new CombMINStrategy();
//        cmin.combMIN(pa.getRuns());
//        cmin.printResults();

//        System.out.println("CombMAX results --------------------");
//        CombMAXStrategy cmax = new CombMAXStrategy();
//        cmax.combMAX(pa.getRuns());
//        cmax.printResults();
//
//        System.out.println("CombSUM results --------------------");
//        CombSUMStrategy csum = new CombSUMStrategy();
//        csum.combSUM(pa.getRuns());
//        csum.printResults();
//
//        System.out.println("CombMED results --------------------");
//        CombMEDStrategy cmed = new CombMEDStrategy();
//        cmed.combMED(pa.getRuns());
//        cmed.printResults();
//
//        System.out.println("CombANZ results --------------------");
//        CombANZStrategy canz = new CombANZStrategy();
//        canz.combANZ(pa.getRuns());
//        canz.printResults();
//
//        System.out.println("CombMNZ results --------------------");
//        CombMNZStrategy cmnz = new CombMNZStrategy();
//        cmnz.combMNZ(pa.getRuns());
//        cmnz.printResults();

    }
}
