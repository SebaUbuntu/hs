public class Main {
    public static void main(String[] args) {
        Domande domande = new Domande();
        domande.importFromFile("/assets/domande.json");

        for (Domanda domanda : domande.getDomande()) {
            System.out.println(domanda);
        }
    }
}
