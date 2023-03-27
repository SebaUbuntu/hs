package praticheautomobilistiche;

import java.util.Calendar;

public class AutoVeicoloIdrogeno extends AutoVeicolo {
    static final double TASSA_K = LettoreConfigurazione.get("AutoVeicoloIdrogeno");

    public AutoVeicoloIdrogeno(String targa, String marca, String modello, int numeroPasseggeri, int anno) {
        super(targa, marca, modello, numeroPasseggeri, anno);
    }

    public double calcolaTassa() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return (year - anno) * TASSA_K;
    }
}
