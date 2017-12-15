package normalizer;

public class Normalizer {
    Double score;
    Double max;
    Double min;
    public Normalizer(Double score, Double max, Double min) {
        this.score = score;
        this.max = max;
        this.min = min;
    }

    /**implements Lee95's normalization
     * */
    public Double normalize(){
        return (score - min) / (max - min);
    }
}
