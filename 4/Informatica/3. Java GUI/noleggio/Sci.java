public class Sci extends Articolo {
    private int lunghezza;
    private boolean daDiscesa;

    public Sci(String marca, String modello, int anno, double prezzoNoleggioGiornaliero,
            String note, int lunghezza, boolean daDiscesa) {
        super(marca, modello, anno, prezzoNoleggioGiornaliero, note);
        this.lunghezza = lunghezza;
        this.daDiscesa = daDiscesa;
    }

    public int getLunghezza() {
        return lunghezza;
    }

    public boolean isDaDiscesa() {
        return daDiscesa;
    }
}
