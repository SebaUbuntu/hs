package praticheautomobilistiche;

abstract public class Veicolo {
    protected String targa;
    protected String marca;
    protected String modello;
    protected int numeroPasseggeri;
    protected int anno;

    protected static LettoreConfigurazione lettoreConfigurazione = new LettoreConfigurazione();

    public Veicolo(String targa, String marca, String modello, int numeroPasseggeri, int anno) {
        this.targa = targa;
        this.marca = marca;
        this.modello = modello;
        this.numeroPasseggeri = numeroPasseggeri;
        this.anno = anno;
    }

    abstract public double calcolaTassa();

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " +
               "Anno: " + anno +
               ", marca: " + marca +
               ", modello: " + modello +
               ", numero passeggeri: " + numeroPasseggeri +
               ", targa: " + targa +
               ", tassa: " + calcolaTassa();
    }
}
