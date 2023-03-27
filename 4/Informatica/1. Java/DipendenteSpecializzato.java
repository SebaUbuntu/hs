import java.time.LocalDate;

public class DipendenteSpecializzato extends Dipendente {
    public DipendenteSpecializzato(String nome, String cognome, LocalDate dataNascita, String residenza, String sesso, double stipendio, int livello) {
        super(nome, cognome, dataNascita, residenza, sesso, stipendio, livello);
    }

    @Override
    public void incrementaLivello() {
        this.livello++;
        this.stipendio += this.stipendio * 0.1;
    }
}
