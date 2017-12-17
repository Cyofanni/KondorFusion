package normalizer;

public class Normalizer {
    Double score;
    Double max;
    Double min;
    public Normalizer(Double score, Double min, Double max) {
        this.score = score;
        this.max = max;
        this.min = min;
    }

    /**implements Lee95's normalization
     * */
    public Double normalize(){
        //System.out.println("score = "+score);
        //System.out.println("min = "+min);
        //System.out.println("max = "+max);
        if(score == null){
            return null;
        }
        return (score - min) / (max - min);
    }
}
