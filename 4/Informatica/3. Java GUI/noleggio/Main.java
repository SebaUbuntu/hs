public class Main {
    public static void main(String[] args) {
        ListaArticoli listaArticoli = new ListaArticoli();
        ListaClienti listaClienti = new ListaClienti();

        listaArticoli.addArticolo(new Sci("Sci", "Sci", 2018, 10, "note", 10, true));
        listaArticoli.addArticolo(new Ciaspole("Ciaspole", "Ciaspole", 2018, 10, "note", 10, 10));
        listaArticoli.addArticolo(new Bob("Bob", "Bob", 2018, 10, "note", 10, "materiale"));
        listaArticoli.addArticolo(new Snowboard("Snowboard", "Snowboard", 2018, 10, "note", "stile", "impostazione"));

        listaClienti.addCliente(new Cliente("Sebastiano", "Barezzi", "Via"));
        listaClienti.addCliente(new Cliente("Cliente", "2", "Via"));

        new GUI(listaArticoli, listaClienti);
    }
}
