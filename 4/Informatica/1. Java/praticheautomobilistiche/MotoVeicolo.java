package praticheautomobilistiche;

public class MotoVeicolo extends Veicolo {
    protected int potenza;
    static final double TASSA_K = LettoreConfigurazione.get("MotoVeicolo");

    public MotoVeicolo(String targa, String marca, String modello, int numeroPasseggeri, int anno,
            int potenza) {
        super(targa, marca, modello, numeroPasseggeri, anno);
        this.potenza = potenza;
    }

    public double calcolaTassa() {
        return potenza * TASSA_K;
    }

    @Override
    public String toString() {
        return super.toString() +
               ", potenza: " + potenza;
    }
}
