package strategies.basic;


import strategies.StrategiesAbs;
import utils.*;
import java.util.List;
import java.util.Map;

public class CombMINStrategy extends StrategiesAbs {
    public CombMINStrategy(){
        runId = "combMIN";
    }

    public void combMIN(List<Topic> runs){

        for(Topic top : runs){
            Map<String, Value[]> temp = top.getMap();

            for(String docId : temp.keySet()){
                Value[] v = temp.get(docId);
                Double score = min(v);
                Document d = new Document(top.getTopic(), docId, -1, score); //rank -1 since rank is not already computed
                results.add(d);
            }
        }
        sort(results);
    }

    private Double min(Value[] values){
        double min = Double.POSITIVE_INFINITY;

        for(int i = 0; i < values.length; i++){
            if(values[i].getScore() == null){
                continue;
            }
            if(values[i].getScore() < min){
                min = values[i].getScore();
            }
        }

        return min;
    }
}
