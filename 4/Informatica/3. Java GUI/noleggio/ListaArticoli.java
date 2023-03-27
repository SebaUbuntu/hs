import java.util.ArrayList;

public class ListaArticoli {
    ArrayList<Articolo> listaArticoli;

    public ListaArticoli() {
        listaArticoli = new ArrayList<Articolo>();
    }

    public ArrayList<Articolo> getListaArticoli() {
        return listaArticoli;
    }

    public ArrayList<Articolo> getListaArticoli(boolean noleggiato) {
        ArrayList<Articolo> listaArticoliFiltrato = new ArrayList<Articolo>();

        for (Articolo articolo : listaArticoli) {
            if (articolo.isNoleggiato() == noleggiato) {
                listaArticoliFiltrato.add(articolo);
            }
        }

        return listaArticoliFiltrato;
    }

    /**
     * Ottieni il numero di elementi
     * @return numero di elementi
     */
    public int getSize() {
        return listaArticoli.size();
    }

    /**
     * Aggiungi un articolo
     * @param articolo articolo da aggiungere
     */
    public void addArticolo(Articolo articolo) {
        listaArticoli.add(articolo);
    }

    /**
     * Rimuovi un articolo
     * @param articolo
     */
    public void removeArticolo(Articolo articolo) {
        listaArticoli.remove(articolo);
    }

    /**
     * Restituisce un articolo dato l'indice
     * @param index
     * @return
     */
    public Articolo getArticolo(int index) {
        return listaArticoli.get(index);
    }

    /**
     * Trova un articolo in base al modello
     * @param modello
     * @return articolo
     */
    public Articolo getArticolo(String modello) {
        for (Articolo articolo : listaArticoli) {
            if (articolo.getModello().equals(modello)) {
                return articolo;
            }
        }

        return null;
    }

    /**
     * Trova un articolo in base a marca e modello
     * @param marca
     * @param modello
     * @return articolo
     */
    public Articolo getArticolo(String marca, String modello) {
        for (Articolo articolo : listaArticoli) {
            if (articolo.getMarca().equals(marca) && articolo.getModello().equals(modello)) {
                return articolo;
            }
        }

        return null;
    }

    /**
     * Restituisce gli articoli noleggiati o non noleggiati a seconda del parametro noleggiati
     * @param noleggiati true se si vuole la lista di articoli noleggiati, false se si vuole quelli non noleggiati
     * @return ArrayList
     */
    public ArrayList<Articolo> getArticoli(boolean noleggiati) {
        ArrayList<Articolo> articoli = new ArrayList<Articolo>();

        for (Articolo articolo : listaArticoli) {
            if (articolo.isNoleggiato() == noleggiati)
                articoli.add(articolo);
        }

        return articoli;
    }

    public double prezzoMedio() {
        double prezzoMedio = 0;

        for (Articolo articolo : listaArticoli) {
            prezzoMedio += articolo.getPrezzoNoleggioGiornaliero();
        }

        return prezzoMedio / listaArticoli.size();
    }
}
