package biblioteca;

import java.time.LocalDate;

public class Prestito {
    private Libro libro;
    private Utente utente;
    private LocalDate dataInizio;
    private LocalDate dataFine;

    public Prestito(Libro libro, Utente utente, LocalDate dataInizio, LocalDate dataFine) {
        this.libro = libro;
        this.utente = utente;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public Libro getLibro() {
        return libro;
    }

    public Utente getUtente() {
        return utente;
    }

    /**
     * Controlla se il prestito è attivo
     * @return true se il prestito è attivo, false altrimenti
     */
    public boolean isAttivo() {
        LocalDate now = LocalDate.now();
        return now.isAfter(dataInizio) && now.isBefore(dataFine);
    }

    /**
     * Restituisce se il prestito è scaduto
     * @return
     */
    public boolean isScaduto() {
        return LocalDate.now().isAfter(dataFine);
    }
}
