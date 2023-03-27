package appartamenti;

public class Abitazione {
    protected int numStanze;
    protected int superficie;
    protected String indirizzo;
    protected String citta;

    public Abitazione() {
        this.numStanze = 0;
        this.superficie = 0;
        this.indirizzo = "";
        this.citta = "";
    }

    public Abitazione(int numStanze, int superficie, String indirizzo, String citta) {
        this.numStanze = numStanze;
        this.superficie = superficie;
        this.indirizzo = indirizzo;
        this.citta = citta;
    }

    public int getNumStanze() {
        return numStanze;
    }

    public int getSuperficie() {
        return superficie;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getCitta() {
        return citta;
    }

    @Override
    public String toString() {
        return "Numero stanze: " + numStanze
                + ", superficie: " + superficie
                + ", indirizzo: " + indirizzo
                + ", citta: " + citta;
    }

    public boolean equals(Abitazione abitazione) {
        return this.numStanze == abitazione.numStanze
               && this.superficie == abitazione.superficie
               && this.indirizzo.equals(abitazione.indirizzo)
               && this.citta.equals(abitazione.citta);
    }
}
