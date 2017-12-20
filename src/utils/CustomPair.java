package utils;

public class CustomPair<S, T>{
    private S fst;
    private T snd;
    public CustomPair(S f, T s){
        fst = f;
        snd = s;
    }

    public S getFst() {
        return fst;
    }

    public T getSnd() {
        return snd;
    }

    public S setFst(S fst){
        this.fst = fst;
        return fst;
    }

    public T setSnd(T snd){
        this.snd = snd;
        return snd;
    }
}
