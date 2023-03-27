public class DomandaRispostaChiusa extends Domanda {
    private String[] opzioni;

    public DomandaRispostaChiusa(Categoria categoria, String domanda, String risposta, String[] opzioni) {
        super(categoria, domanda, risposta);

        this.opzioni = opzioni;
    }

    public String[] getOpzioni() {
        return opzioni;
    }
}
