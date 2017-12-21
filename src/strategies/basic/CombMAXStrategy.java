package strategies.basic;

import strategies.StrategiesAbs;
import utils.*;
import java.util.List;
import java.util.Map;

public class CombMAXStrategy extends StrategiesAbs {

    public void combMAX(List<Topic> runs){

        for(Topic top : runs){
            Map<String, Value[]> temp = top.getMap();

            for(String docId : temp.keySet()){
                Value[] v = temp.get(docId);
                Double score = max(v);
                Document d = new Document(top.getTopic(), docId, -1, score); //rank -1 since rank is not already computed
                results.add(d);
            }
        }
        sort(results);
    }

    private Double max(Value[] values){
        double max = Double.NEGATIVE_INFINITY;

        for(int i = 0; i < values.length; i++){
            if(values[i].getScore() == null){
                continue;
            }
            if(values[i].getScore() > max){
                max = values[i].getScore();
            }
        }

        return max;
    }
}
