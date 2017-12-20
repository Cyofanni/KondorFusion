package utils;

import java.util.Objects;

public class Value {

    Integer rank;
    Double score;

    public Value(Integer rank, Double score) {
        this.rank = rank;
        this.score = score;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Value value = (Value) o;
        return Objects.equals(rank, value.rank) &&
                Objects.equals(score, value.score);
    }

    @Override
    public int hashCode() {

        return Objects.hash(rank, score);
    }

    @Override
    public String toString() {
        return "R: " + rank + " S: " + score;
    }
}
