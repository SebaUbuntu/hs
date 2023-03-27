import java.time.LocalDate;
import java.util.ArrayList;

public class Studenti {
    private ArrayList<Studente> studenti;

    Studenti() {
        studenti = new ArrayList<Studente>();
    }

    public ArrayList<Studente> getStudenti() {
        return studenti;
    }

    public void setStudenti(ArrayList<Studente> studenti) {
        this.studenti = studenti;
    }

    public void inserisciStudente(Studente studente) {
        studenti.add(studente);
    }

    public void rimuoviStudente(int index) {
        studenti.remove(index);
    }

    public void rimuoviStudente(Studente studente) {
        studenti.remove(studente);
    }

    public Studente getStudente(int index) {
        return studenti.get(index);
    }

    public int size() {
        return studenti.size();
    }

    public void removeAll() {
        studenti.clear();
    }

    public static void main(String[] args) {
        Studenti studenti = new Studenti();

        studenti.inserisciStudente(new Studente("Zurlini", "Marco", LocalDate.of(2004, 1, 1), "", "Attack elicopter", 20));
        studenti.inserisciStudente(new Studente("Medioli", "Alex", LocalDate.of(2004, 1, 1), "", "Goblin", 5));
        studenti.inserisciStudente(new Studente("Singh", "Shubhvir", LocalDate.of(2004, 1, 1), "", "Tappeto volante", 18));
        studenti.inserisciStudente(new Studente("Dostomskyi", "Daniil", LocalDate.of(2004, 1, 1), "", "Macedone", 3));

        System.out.println(studenti.getStudente(0));

        studenti.rimuoviStudente(0);
        System.out.println(studenti.getStudente(0));

        for (Studente studente : studenti.getStudenti()) {
            System.out.println(studente);
        }
    }
}
