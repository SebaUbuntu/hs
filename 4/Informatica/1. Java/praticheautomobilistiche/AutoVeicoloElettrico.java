package praticheautomobilistiche;

public class AutoVeicoloElettrico extends AutoVeicolo {
    public AutoVeicoloElettrico(String targa, String marca, String modello, int numeroPasseggeri, int anno) {
        super(targa, marca, modello, numeroPasseggeri, anno);
    }

    public double calcolaTassa() {
        return 0;
    }
}
