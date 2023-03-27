package strumentimusicali;

import java.util.ArrayList;

public class MagazzinoMusicale {
    ArrayList<StrumentoMusicale> magazzino;

    public MagazzinoMusicale() {
        magazzino = new ArrayList<StrumentoMusicale>();
    }

    public void aggiungiStrumento(StrumentoMusicale strumento) {
        magazzino.add(strumento);
    }

    public void eliminaStrumento(StrumentoMusicale strumento) {
        magazzino.remove(strumento);
    }

    /**
     * Dato il nome, restituisce l'oggetto StrumentoMusicale
     * @param nome
     * @return StrumentoMusicale se presente, null altrimenti
     */
    public StrumentoMusicale cercaStrumento(String nome) {
        for (StrumentoMusicale strumento : magazzino) {
            if (strumento.getNome().equals(nome)) {
                return strumento;
            }
        }
        return null;
    }

    /**
     * Restituisce una lista di StrumentiMusicali dato un tipo da filtrare
     * @return stringa contenente informazioni sugli strumenti del tipo richiesto
     */
    public String stampaMagazzino(Class<?> tipo) {
        String s = "";
        for (StrumentoMusicale strumento : magazzino) {
            if (tipo.isAssignableFrom(strumento.getClass())) {
                s += strumento.toString() + "\n";
            }
        }
        return s;
    }

    /**
     * Restituisce una lista di StrumentiMusicali
     * @return stringa contenente informazioni su tutti gli strumenti del magazzino
     */
    public String stampaMagazzino() {
        return stampaMagazzino(StrumentoMusicale.class);
    }

    /**
     * Ottieni lo stumento più antico
     * @return StrumentoMusicale più antico
     */
    public StrumentoMusicale strumentoPiuAntico() {
        StrumentoMusicale strumento = magazzino.get(0);
        for (StrumentoMusicale str : magazzino) {
            if (str.getAnnoInvenzione() < strumento.getAnnoInvenzione()) {
                strumento = str;
            }
        }
        return strumento;
    }
}
