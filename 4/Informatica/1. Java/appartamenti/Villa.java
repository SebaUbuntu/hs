package appartamenti;

public class Villa extends Abitazione {
    protected int numPiani;
    protected int superficieGiardino;
    protected boolean hasPiscina;

    public Villa(int numStanze, int superficie, String indirizzo, String citta, int numPiani, int superficieGiardino, boolean hasPiscina) {
        super(numStanze, superficie, indirizzo, citta);
        this.numPiani = numPiani;
        this.superficieGiardino = superficieGiardino;
        this.hasPiscina = hasPiscina;
    }

    public int getNumPiani() {
        return numPiani;
    }

    public int getSuperficieGiardino() {
        return superficieGiardino;
    }

    public boolean getHasPiscina() {
        return hasPiscina;
    }

    @Override
    public String toString() {
        return super.toString()
               + ", numero piani: " + numPiani
               + ", superficie giardino: " + superficieGiardino
               + ", ha la piscina: " + hasPiscina;
    }

    public boolean equals(Villa villa) {
        return super.equals(villa)
               && this.numPiani == villa.numPiani
               && this.superficieGiardino == villa.superficieGiardino
               && this.hasPiscina == villa.hasPiscina;
    }
}
