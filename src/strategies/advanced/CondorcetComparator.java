package strategies.advanced;

import java.util.Comparator;

public class CondorcetComparator implements Comparator<CondorcetValue> {
    //compute Algorithm 1 simple majority runoff
    //returns -1 if docId1 is ranked better than docId2, 1 otherwise, 0 if they are equal
    //decreasing order is needed
    public int compare(CondorcetValue doc1, CondorcetValue doc2) {
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
                    //count=count+rank1;
                }
                if(rank1 == null) {
                    count--;
                    //count=count-rank2;
                }
            }

            else {
                if(rank1 < rank2){
                    count++; //docId1 is more relevant than docId2 in run runIndex
                    //count = count+(rank2 - rank1);
                }
                else if(rank1 > rank2){
                    count--; //docId1 is less relevant than docId2 in run runIndex
                    //count = count + (rank1 - rank2);
                }
            }
        }

        if(count > 0) {
            System.out.println("document "+doc1.getDocument()+" ranks above or equal to document "+doc2.getDocument()+" count "+count);
            return -1;
        }
        if(count == 0) {
            System.out.println("document "+doc1.getDocument()+" ranks equal to document "+doc2.getDocument()+" count "+count);
            return 0;
        }
        System.out.println("document "+doc1.getDocument()+" ranks below document "+doc2.getDocument()+" count "+count);
        return 1;
    }
}
