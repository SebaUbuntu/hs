package pc;

public class Notebook extends PCPortatile {
    protected boolean webcam;
    protected int risoluzioneWebcam;

    public Notebook(String tipoProcessore, int dimensioneMemoriaRAM, int dimensioneMemoriaMassa, String marca,
            String modello, String sistemaOperativo, int peso, int altezza, int larghezza, int profondita,
            int dimensioniVideo, boolean wireless, boolean webcam, int risoluzioneWebcam) {
        super(tipoProcessore, dimensioneMemoriaRAM, dimensioneMemoriaMassa, marca, modello, sistemaOperativo, peso,
                altezza, larghezza, profondita, dimensioniVideo, wireless);
        this.webcam = webcam;
        this.risoluzioneWebcam = risoluzioneWebcam;
    }

    public boolean getWebcam() {
        return webcam;
    }

    public int getRisoluzioneWebcam() {
        return risoluzioneWebcam;
    }
}
