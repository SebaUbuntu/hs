package biblioteca;

public class Utente {
    private String nome;
    private String cognome;
    private int numeroTessera;

    private static int contatoreNumeroTessera = 0;

    public Utente(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
        this.numeroTessera = contatoreNumeroTessera++;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public int getNumeroTessera() {
        return numeroTessera;
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
                ", cognome: " + cognome +
                ", numero tessera: " + numeroTessera;
    }
}
