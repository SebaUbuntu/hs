package biblioteca;

import java.time.LocalDate;
import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Libro> libri;
    private ArrayList<Prestito> prestiti;

    public Biblioteca() {
        this.libri = new ArrayList<Libro>();
        this.prestiti = new ArrayList<Prestito>();
    }

    /**
     * Aggiunge un libro alla biblioteca e rendilo disponibile al prestito
     * @param libro
     */
    public void aggiungiLibro(Libro libro) {
        libri.add(libro);
    }

    /**
     * Ritira un libro dalla biblioteca e cancella tutti i precedenti prestiti
     * @param libro
     * @return Libro se il libro è stato ritirato, null altrimenti
     */
    public Libro ritiraLibro(Libro libro) {
        // Si può ritirare dalla biblioteca solo se è disponibile
        if (!isDisponibile(libro))
            return null;

        libri.remove(libro);

        // Cancella tutti i prestiti del libro
        for (Prestito prestito : getPrestitiOfLibro(libro)) {
            prestiti.remove(prestito);
        }

        return libro;
    }

    /**
     * Dato un titolo, restituisce il libro corrispondente.
     * @param titolo
     * @return lista di libri con titolo uguale a titolo
     */
    public ArrayList<Libro> cercaLibri(String titolo) {
        ArrayList<Libro> libri = new ArrayList<Libro>();
        for (Libro libro : this.libri)
            if (libro.getTitolo().equals(titolo))
                libri.add(libro);

        return libri;
    }

    /**
     * Dato l'ISBN, restituisce il libro associato.
     * @param isbn_13
     * @return
     */
    public Libro getLibro(String isbn_13) {
        for (Libro libro : this.libri)
            if (libro.getIsbn_13() == isbn_13)
                return libro;

        return null;
    }

    /**
     * Controlla se un libro è disponibile nella biblioteca
     * @param libro
     * @return true se il libro è disponibile, false altrimenti
     */
    public boolean isDisponibile(Libro libro) {
        return libri.contains(libro);
    }

    /**
     * Restituisce lo storico dei prestiti collegati ad un libro
     * @param libro
     * @return lista di prestiti
     */
    public ArrayList<Prestito> getPrestitiOfLibro(Libro libro) {
        ArrayList<Prestito> prestitiOfLibro = new ArrayList<Prestito>();
        for (Prestito prestito : prestiti)
            if (prestito.getLibro().equals(libro))
                prestitiOfLibro.add(prestito);

        return prestitiOfLibro;
    }

    /**
     * Restituisce se il libro è in prestito attivo
     * @param libro
     * @return true se il libro è in prestito attivo, false altrimenti
     */
    public boolean isInPrestitoAttivo(Libro libro) {
        // Se il libro è nella biblioteca, allora non è in prestito
        if (isDisponibile(libro))
            return false;

        for (Prestito prestito : getPrestitiOfLibro(libro))
            // Se c'è un prestito attivo collegato al libro, allora è in prestito
            if (!prestito.isScaduto())
                return true;

        // Altrimenti il libro non è mai stato restituito una volta scaduto il prestito
        return false;
    }

    /**
     * Restituisce se il libro non è stato riportato in biblioteca alla scadenza del prestito
     * Un prestito è scaduto anche quando il libro viene riportato
     * @param libro
     * @return true se il libro non è stato riportato in biblioteca alla scadenza del prestito
     */
    public boolean isInPrestitoScaduto(Libro libro) {
        return !isDisponibile(libro) && !isInPrestitoAttivo(libro);
    }

    /**
     * Prendi in prestito un libro per un mese
     * @param libro
     * @param utente
     * @return Libro se il libro è stato preso in prestito, null altrimenti
     */
    public Libro prendiLibroInPrestito(Libro libro, Utente utente) {
        // Controlla se il libro è disponibile per il prestito
        if (!isDisponibile(libro))
            return null;

        LocalDate now = LocalDate.now();
        prestiti.add(new Prestito(libro, utente, now, now.plusMonths(1)));

        libri.remove(libro);

        return libro;
    }

    public void restituisciLibro(Libro libro) {
        // Controlla se questo libro è mai stato in prestito
        // (fa in modo che non venga restituito un libro non appartenente a questa biblioteca)
        if (getPrestitiOfLibro(libro).size() == 0)
            return;

        libri.add(libro);

        return;
    }
}
