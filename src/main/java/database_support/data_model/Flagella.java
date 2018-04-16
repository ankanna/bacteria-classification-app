package database_support.data_model;

public class Flagella {

    int alpha;
    int beta;
    int number;

    public Flagella(int alpha, int beta, int number) {
        this.alpha = alpha;
        this.beta = beta;
        this.number = number;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getBeta() {
        return beta;
    }

    public void setBeta(int beta) {
        this.beta = beta;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
