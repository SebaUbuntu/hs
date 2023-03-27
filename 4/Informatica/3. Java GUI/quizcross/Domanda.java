public abstract class Domanda {
    private String domanda;
    private Categoria categoria;
    private String risposta;
    private int codice;

    private static int lastCodice = 0;

    public Domanda(Categoria categoria, String domanda, String risposta) {
        this.categoria = categoria;
        this.domanda = domanda;
        this.risposta = risposta;

        this.codice = lastCodice++;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getDomanda() {
        return domanda;
    }

    public String getRisposta() {
        return risposta;
    }

    public int getCodice() {
        return codice;
    }

    @Override
    public String toString() {
        return "domanda: " + domanda +
                ", categoria: " + categoria +
                ", risposta: " + risposta +
                ", codice: " + codice;
    }

    public boolean isCorretto(String rispostaUtente) {
        return rispostaUtente.equals(risposta);
    }
}
