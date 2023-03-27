public class Snowboard extends Articolo {
    private String stile;
    private String impostazione;

    public Snowboard(String marca, String modello, int anno, double prezzoNoleggioGiornaliero,
            String note, String stile, String impostazione) {
        super(marca, modello, anno, prezzoNoleggioGiornaliero, note);

        this.stile = stile;
        this.impostazione = impostazione;
    }

    public String getStile() {
        return stile;
    }

    public String getImpostazione() {
        return impostazione;
    }
}
