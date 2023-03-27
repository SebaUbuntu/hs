import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Studente extends Persona {
    protected int matricola;
    protected ArrayList<Integer> voti;

    private static final int MIN_VOTO = 2;
    private static final int MAX_VOTO = 10;

    private static Random random = new Random();

    public Studente() {
        super();
        this.matricola = 0;
        this.voti = new ArrayList<Integer>();
    }

    public Studente(String nome, String cognome, LocalDate dataNascita, String residenza,
            String sesso, int matricola) {
        super(nome, cognome, dataNascita, residenza, sesso, "Studente");
        this.matricola = matricola;
        this.voti = new ArrayList<Integer>();
    }

    public Studente(Studente studente) {
        super(studente);
        this.matricola = studente.matricola;
        this.voti = studente.voti;
    }

    private void generaVoti(int numVoti) {
        for (int i = 0; i < numVoti; i++)
            voti.add(random.nextInt(MAX_VOTO - MIN_VOTO + 1) + MIN_VOTO);
    }

    public int getMatricola() {
        return matricola;
    }

    public void setMatricola(int matricola) {
        this.matricola = matricola;
    }

    public ArrayList<Integer> getVoti() {
        return voti;
    }

    public void setVoti(ArrayList<Integer> voti) {
        this.voti = voti;
    }

    public double mediaVoti() {
        double avg = 0;

        for (Integer voto : voti)
            avg += voto;
        avg /= voti.size();

        return avg;
    }

    public boolean esito() {
        return mediaVoti() >= 6;
    }

    public String toString() {
        String str = super.toString()
                     + ", matricola: " + matricola
                     + ", voti: ";
        for (Integer voto : voti)
            str += voto + ", ";
        str += "media voti: " + mediaVoti();
        str += ", esito: " + (esito() ? "passato" : "non passato");
        return str;
    }

    public static void main(String[] args) {
        ArrayList<Studente> studenti = new ArrayList<>();
        studenti.add(new Studente("Zurlini", "Marco", LocalDate.of(2004, 1, 1), "", "Attack elicopter", 20));
        studenti.add(new Studente("Medioli", "Alex", LocalDate.of(2004, 1, 1), "", "Goblin", 5));
        studenti.add(new Studente("Singh", "Shubhvir", LocalDate.of(2004, 1, 1), "", "Tappeto volante", 18));
        studenti.add(new Studente("Dostomskyi", "Daniil", LocalDate.of(2003, 1, 1), "", "Macedone", 3));

        for (Studente s : studenti)
            s.generaVoti(5);

        Studente max = studenti.get(0);
        for (Studente s : studenti) {
            if (s.mediaVoti() > max.mediaVoti())
                max = s;
        }
        System.out.println("Studente con media più alta: " + max.nome + " " + max.cognome);

        for (Studente s : studenti) {
            System.out.println(s);
        }
    }
}
