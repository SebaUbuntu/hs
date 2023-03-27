package appartamenti;

public class Appartamento extends Abitazione {
    protected int piano;
    protected boolean hasAscensore;
    protected int numTerrazzi;

    public Appartamento(int numStanze, int superficie, String indirizzo, String citta, int piano, boolean hasAscensore, int numTerrazzi) {
        super(numStanze, superficie, indirizzo, citta);
        this.piano = piano;
        this.hasAscensore = hasAscensore;
        this.numTerrazzi = numTerrazzi;
    }

    public int getPiano() {
        return piano;
    }

    public boolean getHasAscensore() {
        return hasAscensore;
    }

    public int getNumTerrazzi() {
        return numTerrazzi;
    }

    @Override
    public String toString() {
        return super.toString()
               + ", piano: " + piano
               + ", ha l'ascensore: " + hasAscensore
               + ", numero terrazzi: " + numTerrazzi;
    }

    public boolean equals(Appartamento appartamento) {
        return super.equals(appartamento)
               && this.piano == appartamento.piano
               && this.hasAscensore == appartamento.hasAscensore
               && this.numTerrazzi == appartamento.numTerrazzi;
    }
}
