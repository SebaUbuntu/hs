package pc;

public class Desktop extends PCFisso {
    protected String tipoSchedaVideo;
    protected String tipoSchedaAudio;

    public Desktop(String tipoProcessore, int dimensioneMemoriaRAM, int dimensioneMemoriaMassa, String marca,
            String modello, String sistemaOperativo, String tipoCase, String tipoSchedaVideo, String tipoSchedaAudio) {
        super(tipoProcessore, dimensioneMemoriaRAM, dimensioneMemoriaMassa, marca, modello, sistemaOperativo, tipoCase);
        this.tipoSchedaVideo = tipoSchedaVideo;
        this.tipoSchedaAudio = tipoSchedaAudio;
    }

    public String getTipoSchedaVideo() {
        return tipoSchedaVideo;
    }

    public String getTipoSchedaAudio() {
        return tipoSchedaAudio;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Desktop other = (Desktop) obj;
        if (tipoSchedaAudio == null) {
            if (other.tipoSchedaAudio != null)
                return false;
        } else if (!tipoSchedaAudio.equals(other.tipoSchedaAudio))
            return false;
        if (tipoSchedaVideo == null) {
            if (other.tipoSchedaVideo != null)
                return false;
        } else if (!tipoSchedaVideo.equals(other.tipoSchedaVideo))
            return false;
        return true;
    }
}
