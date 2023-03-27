package pc;

public class Server extends PCFisso {
    protected int numeroProcessori;
    protected boolean RAID;

    public Server(String tipoProcessore, int dimensioneMemoriaRAM, int dimensioneMemoriaMassa, String marca,
            String modello, String sistemaOperativo, String tipoCase, int numeroProcessori, boolean RAID) {
        super(tipoProcessore, dimensioneMemoriaRAM, dimensioneMemoriaMassa, marca, modello, sistemaOperativo, tipoCase);
        this.numeroProcessori = numeroProcessori;
        this.RAID = RAID;
    }

    public int getNumeroProcessori() {
        return numeroProcessori;
    }

    public void setNumeroProcessori(int numeroProcessori) {
        this.numeroProcessori = numeroProcessori;
    }

    public boolean getRAID() {
        return RAID;
    }
}
