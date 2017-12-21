package strategies.basic;

import strategies.StrategiesAbs;
import utils.*;
import java.util.List;
import java.util.Map;

public class CombSUMStrategy extends StrategiesAbs {

    public void combSUM(List<Topic> runs){

        for(Topic top : runs){
            Map<String, Value[]> temp = top.getMap();

            for(String docId : temp.keySet()){
                Value[] v = temp.get(docId);
                Double score = sum(v);
                Document d = new Document(top.getTopic(), docId, -1, score); //rank -1 since rank is not already computed
                results.add(d);
            }
        }
        sort(results);
    }

    private Double sum(Value[] values){
        double sum = 0;

        for (int i = 0; i < values.length; i++) {
            if(values[i].getScore() == null){
                continue;
            }
            sum += values[i].getScore();
        }

        return sum;
    }
}
