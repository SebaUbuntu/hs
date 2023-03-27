package pc;

public class PCFisso extends PC {
    protected String tipoCase;

    public PCFisso(String tipoProcessore, int dimensioneMemoriaRAM, int dimensioneMemoriaMassa, String marca,
            String modello, String sistemaOperativo, String tipoCase) {
        super(tipoProcessore, dimensioneMemoriaRAM, dimensioneMemoriaMassa, marca, modello, sistemaOperativo);
        this.tipoCase = tipoCase;
    }

    public String getTipoCase() {
        return tipoCase;
    }
}
