import java.util.ArrayList;

public class CD {
    private String titolo;
    private String autore;
    private String etichettaDiscografica;
    private ArrayList<Canzone> canzoni;

    public CD() {
        this.titolo = "";
        this.autore = "";
        this.etichettaDiscografica = "";
        this.canzoni = new ArrayList<Canzone>();
    }

    public CD(String titolo, String autore, String etichettaDiscografica) {
        this.titolo = titolo;
        this.autore = autore;
        this.etichettaDiscografica = etichettaDiscografica;
        this.canzoni = new ArrayList<Canzone>();
    }

    public CD(CD cd) {
        this.titolo = cd.titolo;
        this.autore = cd.autore;
        this.etichettaDiscografica = cd.etichettaDiscografica;
        this.canzoni = cd.canzoni;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getEtichettaDiscografica() {
        return etichettaDiscografica;
    }

    public void setEtichettaDiscografica(String eticettaDiscografica) {
        this.etichettaDiscografica = eticettaDiscografica;
    }

    public ArrayList<Canzone> getCanzoni() {
        return canzoni;
    }

    public void setCanzoni(ArrayList<Canzone> canzoni) {
        this.canzoni = canzoni;
    }

    /**
     * Aggiunge una canzone al CD
     * @param canzone
     * @return true se la canzone è stata aggiunta, false altrimenti
     */
    public boolean addCanzone(Canzone canzone) {
        return this.canzoni.add(canzone);
    }

    /**
     * Rimuove una canzone dal CD
     * @param canzone
     * @return true se la canzone è stata rimossa, false altrimenti
     */
    public boolean removeCanzone(Canzone canzone) {
        return this.canzoni.remove(canzone);
    }

    /**
     * Ottieni la durata totale del CD
     * @return durata totale del CD
     */
    public int getDurata() {
        int durata = 0;
        for (Canzone canzone : this.canzoni) {
            durata += canzone.getDurata();
        }
        return durata;
    }

    /**
     * Dato un titolo, restituisce la durata della canzone
     * @param titolo
     * @return durata della canzone
     */
    public int getDurataOfCanzone(String titolo) {
        for (Canzone canzone : canzoni) {
            if (canzone.getTitolo().equals(titolo)) {
                return canzone.getDurata();
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        String canzoni_string = "";
        if (!canzoni.isEmpty()) {
            canzoni_string += "\n";

            for (Canzone canzone : canzoni) {
                canzoni_string += "    - " + canzone + "\n";
            }
        } else {
            canzoni_string = "Nessuna canzone";
        }
        return "titolo: " + titolo
               + ", autore: " + autore
               + ", etichetta discografica: " + etichettaDiscografica
               + ", durata: " + getDurata() + " secondi"
               + ", canzoni: " + canzoni_string;
    }

    public static void main(String[] args) {
        CD cd1 = new CD("Titolo 1", "Autore 2", "Etichetta discografica 1");
        cd1.addCanzone(new Canzone("Canzone 1", 180));
        cd1.addCanzone(new Canzone("Canzone 2", 240));
        cd1.addCanzone(new Canzone("Canzone 3", 300));
        cd1.addCanzone(new Canzone("Canzone 4", 360));
        cd1.addCanzone(new Canzone("Canzone 5", 420));

        CD cd2 = new CD("Titolo 2", "Autore 2", "Etichetta discografica 2");
        cd2.addCanzone(new Canzone("Canzone 6", 180));
        cd2.addCanzone(new Canzone("Canzone 7", 240));
        cd2.addCanzone(new Canzone("Canzone 8", 300));
        cd2.addCanzone(new Canzone("Canzone 9", 360));

        System.out.println(cd1);
        System.out.println(cd2);
    }
}
