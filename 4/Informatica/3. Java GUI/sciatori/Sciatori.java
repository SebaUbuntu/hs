import java.util.Random;

public class Sciatori {
    private String nome;
    private String cognome;
    private double tempoPrimaManche;
    private double tempoSecondaManche;

    private static Random random = new Random();

    public Sciatori(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
        tempoPrimaManche = tempoRandom();
        tempoSecondaManche = tempoRandom();
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
               ", cognome: " + cognome +
               ", tempo prima manche: " + tempoPrimaManche +
               ", tempo seconda manche: " + tempoSecondaManche;
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

    public double getTempoPrimaManche() {
        return tempoPrimaManche;
    }

    public double getTempoSecondaManche() {
        return tempoSecondaManche;
    }

    /**
     * Ottieni il nome completo di Sciatore
     * @return nome + cognome
     */
    public String fullName() {
        return nome + " " + cognome;
    }

    /**
     * Genera un tempo random compreso tra 0 e 25
     * @return tempo random
     */
    private double tempoRandom() {
        return random.nextDouble() * 25;
    }

    /**
     * Ottieni il tempo totale di Sciatore
     * @return tempoPrimaManche + tempoSecondaManche
     */
    public double tempoTotale() {
        return tempoPrimaManche + tempoSecondaManche;
    }
}
