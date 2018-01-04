package strategies.advanced;

import runParser.Parser;
import utils.Document;
import utils.Topic;
import utils.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Condorcet extends CondorcetAbs{

    public Condorcet(){
        runId = "Condorcet";
    }
    //compute Algorithm 3 of condorcet 2002 paper
    //this implementation uses a List of CondorcetValue which must be filled and sorted with CondorcetComparator
    //then documents are orderly inserted in results, and ranks are finally computed
    //O(nk logn)
    public void condorcetFuse(List<Topic> runs) {

        for (Topic t: runs) {
            List<CondorcetValue> temp = new ArrayList<>();
            Map<String, Value[]> docs = t.getMap();
            for (String document: docs.keySet()) {
                Value[] values = docs.get(document);
                Integer[] ranks = new Integer[values.length];
                for (int runIndex = 0; runIndex < values.length; runIndex++) {
                    ranks[runIndex] = values[runIndex].getRank();
                }
                temp.add(new CondorcetValue(t.getTopic(), document, ranks));
            }
            temp.sort(new CondorcetComparator());
            for (CondorcetValue v: temp) {
                //scores not defined in Condorcet fusion technique, ranks computed later
                results.add(new Document(t.getTopic(), v.getDocument(), -1, null));
            }
        }

        computeRanks(results);
    }

    public static void main(String[] args) {
        Parser pa = new Parser();
        pa.readAndNormalize("/home/marco/Scrivania/run");
        pa.printRuns();

        Condorcet con = new Condorcet();
        con.condorcetFuse(pa.getRuns());
        con.printResults();
    }
}
