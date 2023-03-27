public class Articolo {
    static private int lastCodiceUnivoco = 0;

    private int codiceUnivoco;
    private String marca;
    private String modello;
    private int anno;
    private double prezzoNoleggioGiornaliero;
    private String note;

    private Cliente noleggiante;

    public Articolo(String marca, String modello, int anno, double prezzoNoleggioGiornaliero,
            String note) {
        this.codiceUnivoco = lastCodiceUnivoco++;
        this.marca = marca;
        this.modello = modello;
        this.anno = anno;
        this.prezzoNoleggioGiornaliero = prezzoNoleggioGiornaliero;
        this.note = note;

        this.noleggiante = null;
    }

    public int getCodiceUnivoco() {
        return codiceUnivoco;
    }

    public String getMarca() {
        return marca;
    }

    public String getModello() {
        return modello;
    }

    public int getAnno() {
        return anno;
    }

    public double getPrezzoNoleggioGiornaliero() {
        return prezzoNoleggioGiornaliero;
    }

    public String getNote() {
        return note;
    }

    public Cliente getNoleggiante() {
        return noleggiante;
    }

    public String fullName() {
        return marca + " " + modello + " (" + anno + ")";
    }

    public void setNoleggiante(Cliente noleggiante) {
        this.noleggiante = noleggiante;
    }

    /**
     * Controlla se l'articolo è noleggiato
     * @return true se l'articolo è noleggiato, false altrimenti
     */
    public boolean isNoleggiato() {
        return noleggiante != null;
    }

    /**
     * Restituisci l'articolo
     */
    public void restituisci() {
        setNoleggiante(null);
    }

    @Override
    public String toString() {
        return "Nome: " + fullName() +
               ", prezzo noleggio: " + prezzoNoleggioGiornaliero + "euro/giorno" +
               ", note: " + note +
               ", noleggiato: " + (isNoleggiato() ? "si" : "no") +
               ", noleggiante: " + ((noleggiante != null) ? noleggiante.fullName() : "nessuno");
    }
}
