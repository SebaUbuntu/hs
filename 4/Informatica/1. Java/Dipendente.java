import java.time.LocalDate;

public class Dipendente extends Persona {
    protected double stipendio;
    protected int livello;

    public Dipendente(String nome, String cognome, LocalDate dataNascita, String residenza, String sesso, double stipendio, int livello) {
        super(nome, cognome, dataNascita, residenza, sesso, "Dipendente");
        setStipendio(stipendio);
        setLivello(livello);
    }

    public double getStipendio() {
        return stipendio;
    }

    public void setStipendio(double stipendio) {
        this.stipendio = stipendio;
    }

    public int getLivello() {
        return livello;
    }

    public void setLivello(int livello) {
        // Controllo che il livello sia compreso tra 0 e 7
        if (livello < 0 || livello > 7)
            return;

        // Controllo che il livello non sia minore del livello del dipendente
        if (livello < this.livello)
            return;

        // Aumenta lo stipendio del dipendente di 10% per ogni livello superato
        while (livello > this.livello) {
            incrementaLivello();
        }
    }

    public void incrementaLivello() {
        this.livello++;
        this.stipendio += this.stipendio * 0.1;
    }

    @Override
    public String toString() {
        return super.toString()
               + ", stipendio: " + this.stipendio + "€"
               + ", livello: " + this.livello;
    }

    public static void main(String[] args) {
        Dipendente d = new Dipendente("Mario", "Rossi", LocalDate.of(1990, 10, 2),
                                      "Uomo", "Via Roma, 1", 1000, 0);

        System.out.println(d);

        // Aumento di livello
        d.setLivello(1);
        System.out.println(d);
    }
}
