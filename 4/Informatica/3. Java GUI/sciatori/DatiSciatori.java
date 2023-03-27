import java.util.ArrayList;

public class DatiSciatori {
    private ArrayList<Sciatori> sciatori;

    public DatiSciatori() {
        sciatori = new ArrayList<Sciatori>();
    }

    public DatiSciatori(ArrayList<Sciatori> sciatori) {
        this.sciatori = sciatori;
    }

    public ArrayList<Sciatori> getSciatori() {
        return sciatori;
    }

    public int size() {
        return sciatori.size();
    }

    /**
     * Aggiungi un nuovo Sciatore alla lista
     * @param s Sciatore da aggiungere
     */
    public void aggiungiSciatore(Sciatori s) {
        sciatori.add(s);
    }

    /**
     * Ritorna l'oggetto Sciatore nell'indice specificato
     * @param i indice
     * @return Sciatore
     */
    public Sciatori trovaSciatore(int i) {
        return sciatori.get(i);
    }

    /**
     * Ritorna il numero di Sciatori con nome specificato
     * @param nome
     * @return Sciatore
     */
    public Sciatori trovaSciatore(String nome) {
        for (Sciatori sciatore : sciatori) {
            if (sciatore.getNome().equals(nome))
                return sciatore;
        }
        return null;
    }

    /**
     * Dato uno Sciatore e una manche, ottieni la posizione sul podio
     * @param sciatore Sciatore
     * @param primaManche true se si tratta della prima manche, false se si tratta della seconda
     */
    public int getPosizione(Sciatori sciatore, boolean primaManche) {
        int posizione = 1;
        for (Sciatori s : sciatori) {
            if (s == sciatore)
                continue;

            if (primaManche && s.getTempoPrimaManche() > sciatore.getTempoPrimaManche())
                posizione++;
            else if (!primaManche && s.getTempoSecondaManche() > sciatore.getTempoSecondaManche())
                posizione++;
        }
        return posizione;
    }

    /**
     * Ottieni il vincitore della gara
     * @return Sciatore
     */
    public Sciatori vincitore() {
        Sciatori best_sciatore = null;
        for (Sciatori sciatore : sciatori)
            if (best_sciatore == null || best_sciatore.tempoTotale() < sciatore.tempoTotale()) {
                best_sciatore = sciatore;
            }
        return best_sciatore;
    }
}
