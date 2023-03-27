public class Cliente {
    private String nome;
    private String cognome;
    private String indirizzo;

    public Cliente(String nome, String cognome, String indirizzo) {
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String fullName() {
        return nome + " " + cognome;
    }

    @Override
    public String toString() {
        return "Nome: " + fullName() +
               ", indirizzo: " + indirizzo;
    }
}
