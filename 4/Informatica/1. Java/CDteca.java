import java.util.ArrayList;

public class CDteca {
    private ArrayList<CD> listaCD;

    public CDteca() {
        listaCD = new ArrayList<CD>();
    }

    /**
     * Aggiunge un CD alla lista
     * @param cd
     */
    public void addCD(CD cd) {
        listaCD.add(cd);
    }

    /**
     * Dato il titolo di un CD, restituisce il CD
     * @param titolo
     * @return CD
     */
    public CD cercaCD(String titolo) {
        for (CD cd : listaCD) {
            if (cd.getTitolo().equals(titolo)) {
                return cd;
            }
        }
        return null;
    }

    /**
     * Dato il titolo e l'autore di un CD, restituisce il CD
     * @param titolo
     * @param autore
     * @return CD
     */
    public CD cercaCD(String titolo, String autore) {
        for (CD cd : listaCD) {
            if (cd.getTitolo().equals(titolo) && cd.getAutore().equals(autore)) {
                return cd;
            }
        }
        return null;
    }

    /**
     * Dato il titolo, rimuove un CD dalla lista
     * @param titolo
     */
    public void removeCD(String titolo) {
        CD cd = cercaCD(titolo);
        if (cd != null) {
            listaCD.remove(cd);
        }
    }

    /**
     * Dato l'index di un CD, rimuove un CD dalla lista
     * @param index
     */
    public void removeCD(int index) {
        listaCD.remove(index);
    }

    /**
     * Restituisce il numero di CD presenti nella lista
     * @return numero di CD
     */
    public int getNumCD() {
        return listaCD.size();
    }

    /**
     * Ottieni il CD con durata minore
     * @return CD
     */
    public CD cdConDurataMinore() {
        CD minCD = listaCD.get(0);
        for (CD cd : listaCD) {
            if (cd.getDurata() < minCD.getDurata()) {
                minCD = cd;
            }
        }
        return minCD;
    }

    /**
     * Restituisce la lista dei CD fatti dallo stesso autore
     * @param autore
     * @return
     */
    public ArrayList<CD> cdConStessoAutore(String autore) {
        ArrayList<CD> listaCDConStessoAutore = new ArrayList<CD>();

        for (CD cd : listaCD)
            if (cd.getAutore().equals(autore))
                listaCDConStessoAutore.add(cd);

        return listaCDConStessoAutore;
    }

    public static void main(String[] args) {
        CDteca cdteca = new CDteca();

        CD cd1 = new CD("Titolo 1", "Autore 1", "Etichetta discografica 1");
        cd1.addCanzone(new Canzone("Canzone 1", 180));
        cd1.addCanzone(new Canzone("Canzone 2", 240));
        cd1.addCanzone(new Canzone("Canzone 3", 300));
        cdteca.addCD(cd1);

        CD cd2 = new CD("Titolo 2", "Autore 1", "Etichetta discografica 2");
        cd2.addCanzone(new Canzone("Canzone 4", 180));
        cd2.addCanzone(new Canzone("Canzone 5", 240));
        cdteca.addCD(cd2);

        CD cd3 = new CD("Titolo 3", "Autore 2", "Etichetta discografica 3");
        cd3.addCanzone(new Canzone("Canzone 6", 180));
        cd3.addCanzone(new Canzone("Canzone 7", 240));
        cd3.addCanzone(new Canzone("Canzone 8", 300));
        cdteca.addCD(cd3);

        System.out.println("CD con durata minore: " + cdteca.cdConDurataMinore());
        System.out.println("CD con stesso autore: ");
        for (CD cd : cdteca.cdConStessoAutore("Autore 1"))
            System.out.println(cd);
    }
}
