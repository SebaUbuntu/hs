import java.util.ArrayList;

public class ListaClienti {
    private ArrayList<Cliente> listaClienti;

    public ListaClienti() {
        listaClienti = new ArrayList<Cliente>();
    }

    /**
     * Ottieni la lista dei clienti
     * @return
     */
    public ArrayList<Cliente> getListaClienti() {
        return listaClienti;
    }

    /**
     * Ottieni il numero di elementi
     * @return numero di elementi
     */
    public int getSize() {
        return listaClienti.size();
    }

    /**
     * Aggiungi un cliente
     * @param articolo articolo da aggiungere
     */
    public void addCliente(Cliente articolo) {
        listaClienti.add(articolo);
    }

    /**
     * Rimuovi un cliente
     * @param articolo
     */
    public void removeCliente(Cliente articolo) {
        listaClienti.remove(articolo);
    }

    /**
     * Restituisce un cliente dato l'indice
     * @param index
     * @return
     */
    public Cliente getCliente(int index) {
        return listaClienti.get(index);
    }
}
