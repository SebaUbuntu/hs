public class Ciaspole extends Articolo {
    private int misura;
    private int galleggiamento;

    public Ciaspole(String marca, String modello, int anno, double prezzoNoleggioGiornaliero,
            String note, int misura, int galleggiamento) {
        super(marca, modello, anno, prezzoNoleggioGiornaliero, note);
        this.misura = misura;
        this.galleggiamento = galleggiamento;
    }

    public int getMisura() {
        return misura;
    }

    public int getGalleggiamento() {
        return galleggiamento;
    }
}
