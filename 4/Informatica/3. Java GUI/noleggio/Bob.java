public class Bob extends Articolo {
    private int personeTrasportabili;
    private String materiale;

    public Bob(String marca, String modello, int anno, double prezzoNoleggioGiornaliero,
            String note, int personeTrasportabili, String materiale) {
        super(marca, modello, anno, prezzoNoleggioGiornaliero, note);

        this.personeTrasportabili = personeTrasportabili;
        this.materiale = materiale;
    }

    public int getPersoneTrasportabili() {
        return personeTrasportabili;
    }

    public String getMateriale() {
        return materiale;
    }
}
