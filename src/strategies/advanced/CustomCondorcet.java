package strategies.advanced;

import runParser.Parser;
import utils.Document;
import utils.Topic;
import utils.Value;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class CustomCondorcet extends CondorcetAbs{
    public CustomCondorcet(){
        runId = "CustomCondorcet";
    }

     //compute modified Algorithm 3 of condorcet 2002 paper
     //uses an array of CondorcetValue which is created and sorted with mergesort for each topic
     //Alternative version of Condorcet Class
     //Complexity O(nk logn)
     public void condorcetFuse(List<Topic> runs) {
         for (Topic t: runs) {
             Map<String, Value[]> docs = t.getMap();
             CondorcetValue[] temp = new CondorcetValue[docs.size()];
             int index = 0;
             for (String document: docs.keySet()) {
                 Value[] values = docs.get(document);
                 Integer[] ranks = new Integer[values.length];
                 for (int runIndex = 0; runIndex < values.length; runIndex++) {
                     ranks[runIndex] = values[runIndex].getRank();
                 }

                 temp[index] = new CondorcetValue(t.getTopic(), document, ranks);
                 index++;
             }

             sort(temp);

             for (CondorcetValue cv: temp) {
                 results.add(new Document(t.getTopic(), cv.getDocument(), -1, null));
             }
         }

         computeRanks(results);
     }

     private void sort(CondorcetValue[] values){
         if(values.length <= 1)
             return;
         int mid = values.length / 2;

         CondorcetValue[] left = new CondorcetValue[mid];
         CondorcetValue[] right = new CondorcetValue[values.length - mid];

         for (int i = 0; i<left.length; i++)
             left[i] = values[i];
         for (int i = 0; i<right.length; i++)
                 right[i] = values[mid+i];

         sort(left);
         sort(right);

         merge(values, left, right);
     }

     private void merge(CondorcetValue[] values, CondorcetValue[] left, CondorcetValue[] right){
         int i = 0; int leftIndex = 0; int rightIndex = 0;

         while(leftIndex < left.length && rightIndex < right.length) {
             if(condorcetCompare(left[leftIndex], right[rightIndex]))
                 values[i++] = left[leftIndex++];
             else
                 values[i++] = right[rightIndex++];
         }

         while(leftIndex < left.length)
             values[i++] = left[leftIndex++];

         while(rightIndex < right.length)
             values[i++] = right[rightIndex++];
     }

     //compute Algorithm 1 simple majority runoff
     //returns true if docId1 is ranked better than docId2, false otherwise
     private boolean condorcetCompare(CondorcetValue doc1, CondorcetValue doc2){
         Integer[] ranksDoc1 = doc1.getRanks();
         Integer[] ranksDoc2 = doc2.getRanks();

         int count = 0;
         Integer rank1;
         Integer rank2;
         for(int runIndex = 0; runIndex < ranksDoc1.length; runIndex++){
             rank1 = ranksDoc1[runIndex];
             rank2 = ranksDoc2[runIndex];

             //deal with null values which may occur when docIdx is not returned in run runIndex
             if(rank1 == null || rank2 == null) {
                 if(rank2 == null) {
                     count++;
                 }
                 if(rank1 == null) {
                     count--;
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
             //System.out.println("document "+doc1.getDocument()+" ranks above to document "+doc2.getDocument()+" count "+count);
             return false;
         }

         //System.out.println("document "+doc1.getDocument()+" ranks below document "+doc2.getDocument()+" count "+count);
         return true;
     }

     public static void main(String[] args) {
         Parser pa = new Parser();
         pa.readAndNormalize("/home/davide/Desktop/RUNs");
         //pa.printRuns();

         CustomCondorcet con = new CustomCondorcet();
         con.condorcetFuse(pa.getRuns());
         con.printResults();
     }
}