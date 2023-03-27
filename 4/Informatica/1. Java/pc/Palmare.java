package pc;

public class Palmare extends PCPortatile {
    protected boolean bluetooth;
    protected String tipoEspansione;

    public Palmare(String tipoProcessore, int dimensioneMemoriaRAM, int dimensioneMemoriaMassa, String marca,
            String modello, String sistemaOperativo, int peso, int altezza, int larghezza, int profondita,
            int dimensioniVideo, boolean wireless, boolean bluetooth, String tipoEspansione) {
        super(tipoProcessore, dimensioneMemoriaRAM, dimensioneMemoriaMassa, marca, modello, sistemaOperativo, peso,
                altezza, larghezza, profondita, dimensioniVideo, wireless);
        this.bluetooth = bluetooth;
        this.tipoEspansione = tipoEspansione;
    }

    public boolean getBluetooth() {
        return bluetooth;
    }

    public String getTipoEspansione() {
        return tipoEspansione;
    }
}
