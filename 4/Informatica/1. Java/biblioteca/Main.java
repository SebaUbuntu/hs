package biblioteca;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.aggiungiLibro(new Libro("Il Signore degli Anelli", "John R. R. Tolkien", "978-8830104716", 1364));
        biblioteca.aggiungiLibro(new Libro("Il Piccolo Principe", "Antoine de Saint-Exupéry", "978-8854172388", 137));

        Utente utente = new Utente("Sebastiano", "Barezzi");
        Libro piccolo_principe = biblioteca.getLibro("978-8854172388");
        System.out.println(piccolo_principe);

        biblioteca.prendiLibroInPrestito(piccolo_principe, utente);
        System.out.println("Il Piccolo Principe è disponibile: " + biblioteca.isDisponibile(piccolo_principe));
        biblioteca.restituisciLibro(piccolo_principe);
        System.out.println("Il Piccolo Principe è disponibile: " + biblioteca.isDisponibile(piccolo_principe));
    }
}
