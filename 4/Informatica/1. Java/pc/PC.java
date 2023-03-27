package pc;

public class PC {
    protected String tipoProcessore;
    protected int dimensioneMemoriaRAM;
    protected int dimensioneMemoriaMassa;
    protected String marca;
    protected String modello;
    protected String sistemaOperativo;

    public PC(String tipoProcessore, int dimensioneMemoriaRAM, int dimensioneMemoriaMassa, String marca, String modello,
            String sistemaOperativo) {
        this.tipoProcessore = tipoProcessore;
        this.dimensioneMemoriaRAM = dimensioneMemoriaRAM;
        this.dimensioneMemoriaMassa = dimensioneMemoriaMassa;
        this.marca = marca;
        this.modello = modello;
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getTipoProcessore() {
        return tipoProcessore;
    }

    public int getDimensioneMemoriaRAM() {
        return dimensioneMemoriaRAM;
    }

    public int getDimensioneMemoriaMassa() {
        return dimensioneMemoriaMassa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModello() {
        return modello;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }
}
