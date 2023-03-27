package biblioteca;

public class Libro {
    private String titolo;
    private String autore;
    private String isbn_13;
    private int numeroPagine;

    public Libro(String titolo, String autore, String isbn_13, int numeroPagine) {
        this.titolo = titolo;
        this.autore = autore;
        this.isbn_13 = isbn_13;
        this.numeroPagine = numeroPagine;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAutore() {
        return autore;
    }

    public String getIsbn_13() {
        return isbn_13;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    @Override
    public String toString() {
        return "Titolo: " + titolo +
                ", autore: " + autore +
                ", isbn_13: " + isbn_13 +
                ", numero pagine: " + numeroPagine;
    }
}
