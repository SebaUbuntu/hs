package praticheautomobilistiche;

import java.util.Calendar;

public class AutoVeicoloGas extends AutoVeicolo {
    public enum Tipo {
        GPL,
        METANO,
    }

    protected Tipo tipo;
    protected int potenza;
    static final double TASSA_K_GPL = LettoreConfigurazione.get("AutoVeicoloGasGPL");
    static final double TASSA_K_METANO = LettoreConfigurazione.get("AutoVeicoloGasMetano");

    public AutoVeicoloGas(String targa, String marca, String modello, int numeroPasseggeri, int anno,
            Tipo tipo, int potenza) {
        super(targa, marca, modello, numeroPasseggeri, anno);
        this.tipo = tipo;
        this.potenza = potenza;
    }

    public double calcolaTassa() {
        int year = Calendar.getInstance().get(Calendar.YEAR);

        if (anno + 5 > year)
            return 0;

        return potenza * (tipo == Tipo.GPL ? TASSA_K_GPL : TASSA_K_METANO);
    }

    @Override
    public String toString() {
        return super.toString() +
               ", tipo: " + (tipo == Tipo.GPL ? "GPL" : "metano") +
               ", potenza: " + potenza;
    }
}
