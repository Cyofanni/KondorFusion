package runParser;

/*Stub main to test the parser before its use by the algos */

public class StubMainTestParser {
    public static void main(String[] args){
        Parser pa = new Parser();
        pa.readAndNormalize("/home/marco/Scrivania/test");
        pa.printRuns();
    }
}
