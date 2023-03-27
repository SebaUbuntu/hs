public class Libro {
    private String titolo;
    private String autore;
    private int numeroPagine;
    private double costoPagina;
    private static final double COSTO_FISSO = 5.5;

    public Libro(String titolo, String autore, int numeroPagine) {
        this.titolo = titolo;
        this.autore = autore;
        this.numeroPagine = numeroPagine;
    }

    public Libro() {
        titolo = "Senza titolo";
        autore = "Sconosciuto";
        numeroPagine = 0;
        costoPagina = 0;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public double getCostoPagina() {
        return costoPagina;
    }

    public void setCostoPagina(double costoPagina) {
        this.costoPagina = costoPagina;
    }

    public double calcolaPrezzo() {
        return COSTO_FISSO + numeroPagine * costoPagina;
    }
}
