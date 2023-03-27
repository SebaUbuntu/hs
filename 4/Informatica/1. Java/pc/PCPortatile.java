package pc;

public class PCPortatile extends PC {
    protected int peso;
    protected int altezza;
    protected int larghezza;
    protected int profondita;
    protected int dimensioniVideo;
    protected boolean wireless;

    public PCPortatile(String tipoProcessore, int dimensioneMemoriaRAM, int dimensioneMemoriaMassa, String marca,
            String modello, String sistemaOperativo, int peso, int altezza, int larghezza, int profondita,
            int dimensioniVideo, boolean wireless) {
        super(tipoProcessore, dimensioneMemoriaRAM, dimensioneMemoriaMassa, marca, modello, sistemaOperativo);
        this.peso = peso;
        this.altezza = altezza;
        this.larghezza = larghezza;
        this.profondita = profondita;
        this.dimensioniVideo = dimensioniVideo;
        this.wireless = wireless;
    }

    public int getPeso() {
        return peso;
    }

    public int getAltezza() {
        return altezza;
    }

    public int getLarghezza() {
        return larghezza;
    }

    public int getProfondita() {
        return profondita;
    }

    public int getDimensioniVideo() {
        return dimensioniVideo;
    }

    public boolean getWireless() {
        return wireless;
    }
}
