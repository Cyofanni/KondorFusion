package strategies.advanced;

import runParser.Parser;
import utils.Document;
import utils.Topic;
import utils.Value;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class CustomCondorcet extends CondorcetAbs{

     //compute modified Algorithm 3 of condorcet 2002 paper
     //no support structures needed
     //comparison between document pairs is computed on the fly
     //
     //O(kn^2) instead of O(nk logn), since it is similar to selection sort algorithm
     public void condorcetFuse(List<Topic> runs) {
         for (Topic t: runs) {
             Map<String, Value[]> docs = t.getMap();
             Iterator<String> iter = docs.keySet().iterator(); //use iterator to avoid concurrent modification exception
             while(iter.hasNext()) {
                 boolean comp = true;
                 String docId1 = iter.next();

                 for (String docId2: docs.keySet()) {
                     comp = comp && condorcetCompare(docs, docId1, docId2);
                 }

                 if(comp) {
                     //scores not defined in Condorcet fusion technique, ranks computed later
                     Document doc = new Document(t.getTopic(), docId1, -1, null);
                     results.add(doc);
                     //remove document operation needed
                     iter.remove();
                 }
             }
         }

         computeRanks(results);
     }

     //compute Algorithm 1 simple majority runoff
     //returns true if docId1 is ranked better than docId2, false otherwise
     private boolean condorcetCompare(Map<String, Value[]> docs, String docId1, String docId2){
         Value[] ranksDoc1 = docs.get(docId1);
         Value[] ranksDoc2 = docs.get(docId2);
         int count = 0;
         Integer rank1;
         Integer rank2;
         for(int runIndex = 0; runIndex < ranksDoc1.length; runIndex++){
             rank1 = ranksDoc1[runIndex].getRank();
             rank2 = ranksDoc2[runIndex].getRank();

             //deal with null values which may occur when docIdx is not returned in run runIndex
             //this implementation privileges the first keys in the map, i.e. the first run
             //
             // example:
             // doc1 R={100,null}, doc2 R={null, 1} are considered as equal,
             // but are they actually? Other Ideas?
             //

             if(rank1 == null || rank2 == null) {
                 if(rank2 == null) {
                     count++;
                     //count=count+5; //test: null value for docId2 in a run counts five weights for docId1
                 }
                 if(rank1 == null) {
                     count--;
                     //count=count-5;
                 }
             }

             else {
                 if(rank1 < rank2){
                     count++; //docId1 is more relevant than docId2 in run runIndex
                 }
                 else if(rank1 > rank2){
                     count--; //docId1 is less relevant than docId2 in run runIndex
                 }
             }
         }

         if(count >= 0) {
             System.out.println("document "+docId1+" ranks above or equal to document "+docId2+" count "+count);
             return true;
         }
         System.out.println("document "+docId1+" ranks below document "+docId2+" count "+count);
         return false;
     }

     public static void main(String[] args) {
         Parser pa = new Parser();
         pa.readAndNormalize("/home/davide/Desktop/test");
         pa.printRuns();

         strategies.advanced.Condorcet con = new strategies.advanced.Condorcet();
         con.condorcetFuse(pa.getRuns());
         con.printResults();
     }
}