package runParser;

/*Stub main to test the parser before its use by the algos */

public class StubMainTestParser {
    public static void main(String[] args){
        ParserAbs pa = new Parser();
        pa.readAndNormalize("../../BM25b0.75_2.res");
        pa.printMap();
    }
}
