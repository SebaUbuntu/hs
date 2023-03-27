import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Persona {
    protected String nome;
    protected String cognome;
    protected LocalDate dataNascita;
    protected String residenza;
    protected String sesso;
    protected String professione;

    public static void main(String[] args) {
        Persona persona = new Persona();
        System.out.println(persona.toString());
    }

    public Persona() {
        this.nome = "Sconosciuto";
        this.cognome = "Sconosciuto";
        this.dataNascita = LocalDate.of(1900, 1, 1);
        this.residenza = "Sconosciuta";
        this.sesso = "Sconosciuto";
        this.professione = "Sconosciuto";
    }

    public Persona(String nome, String cognome, LocalDate dataNascita, String residenza, String sesso, String professione) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.residenza = residenza;
        this.sesso = sesso;
        this.professione = professione;
    }

    /**
     * Costruttore di copia
     * @param persona
     */
    public Persona(Persona persona) {
        this.nome = persona.nome;
        this.cognome = persona.cognome;
        this.dataNascita = persona.dataNascita;
        this.residenza = persona.residenza;
        this.sesso = persona.sesso;
        this.professione = persona.professione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getResidenza() {
        return residenza;
    }

    public void setResidenza(String residenza) {
        this.residenza = residenza;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getProfessione() {
        return professione;
    }

    public void setProfessione(String professione) {
        this.professione = professione;
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Nome " + nome
                + ", cognome " + cognome
                + ", data nascita: " + dataNascita.format(formatter)
                + ", sesso: " + sesso
                + ", professione: " + professione;
    }
}
