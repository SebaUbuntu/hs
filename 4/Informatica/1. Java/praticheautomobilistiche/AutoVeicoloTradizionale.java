package praticheautomobilistiche;

public class AutoVeicoloTradizionale extends AutoVeicolo {
    public enum Tipo {
        BENZINA,
        GASOLIO,
    }

    protected Tipo tipo;
    protected int potenza;
    static final double TASSA_K = LettoreConfigurazione.get("AutoVeicoloTradizionale");

    public AutoVeicoloTradizionale(String targa, String marca, String modello, int numeroPasseggeri, int anno,
            Tipo tipo, int potenza) {
        super(targa, marca, modello, numeroPasseggeri, anno);
        this.tipo = tipo;
        this.potenza = potenza;
    }

    public double calcolaTassa() {
        return potenza * TASSA_K;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", tipo: " + (tipo == Tipo.BENZINA ? "benzina" : "gasolio") +
                ", potenza: " + potenza;
    }
}
