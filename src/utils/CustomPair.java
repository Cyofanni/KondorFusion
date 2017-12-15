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
}
