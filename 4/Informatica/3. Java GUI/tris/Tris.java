import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tris implements ActionListener, WindowListener {
    static public final String NAME = "Tris";

    static public final int LARGHEZZA_FINESTRA = 400;
    static public final int ALTEZZA_FINESTRA = 400;

    static public final int RIGHE = 3;
    static public final int COLONNE = 3;
    static public final int MAX_CASELLE = RIGHE * COLONNE;

    static private Random random = new Random();

    JFrame frame;
    JPanel labelPanel;
    JLabel label;
    JPanel casellePanel;
    Casella[] caselle;
    int caselleRiempite;
    Giocatore turno;

    public Tris() {
        frame = new JFrame();
        labelPanel = new JPanel();
        label = new JLabel();
        casellePanel = new JPanel();
        caselle = new Casella[MAX_CASELLE];
        caselleRiempite = 0;

        frame.addWindowListener(this);
        frame.setSize(LARGHEZZA_FINESTRA, ALTEZZA_FINESTRA);
        frame.setTitle(NAME);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        label.setHorizontalAlignment(JLabel.CENTER);

        labelPanel.setLayout(new BorderLayout());
        labelPanel.setBounds(0, 0, LARGHEZZA_FINESTRA, 100);
        labelPanel.add(label);

        casellePanel.setLayout(new GridLayout(RIGHE, COLONNE));

        for (int i = 0; i < caselle.length; i++) {
            caselle[i] = new Casella();
            caselle[i].addActionListener(this);
            casellePanel.add(caselle[i]);
        }

        frame.add(labelPanel, BorderLayout.NORTH);
        frame.add(casellePanel);

        setTurno(random.nextBoolean() ? Giocatore.X : Giocatore.O);
    }

    /**
     * Controlla le caselle e ritorna il giocatore vincitore.
     * Ritornerà SCONOSCIUTO se non c'è un vincitore.
     * @param p1 prima casella
     * @param p2 seconda casella
     * @param p3 terza casella
     * @return giocatore vincitore
     */
    private Giocatore checkCaselle(int p1, int p2, int p3) {
        Giocatore giocatore = caselle[p1].getGiocatore();
        if (giocatore == Giocatore.SCONOSCIUTO)
            return Giocatore.SCONOSCIUTO;

        return (giocatore == caselle[p2].getGiocatore() && giocatore == caselle[p3].getGiocatore())
                ? giocatore
                : Giocatore.SCONOSCIUTO;
    }

    /**
     * Controlla se c'è un vincitore.
     */
    private void controllaVincitore() {
        Giocatore giocatore;

        // Controlla le righe
        for (int i = 0; i < caselle.length; i += COLONNE) {
            giocatore = checkCaselle(i, i+1, i+2);
            if (giocatore != Giocatore.SCONOSCIUTO) {
                endGame(giocatore, i, i+1, i+2);
                return;
            }
        }

        // Controlla le colonne
        for (int i = 0; i < COLONNE; i++) {
            giocatore = checkCaselle(i, i+COLONNE, i+COLONNE*2);
            if (giocatore != Giocatore.SCONOSCIUTO) {
                endGame(giocatore, i, i+COLONNE, i+COLONNE*2);
                return;
            }
        }

        // Controlla le diagonali
        giocatore = checkCaselle(0, 4, 8);
        if (giocatore != Giocatore.SCONOSCIUTO) {
            endGame(giocatore, 0, 4, 8);
            return;
        }

        giocatore = checkCaselle(2, 4, 6);
        if (giocatore != Giocatore.SCONOSCIUTO) {
            endGame(giocatore, 2, 4, 6);
            return;
        }

        // Controlla se tutte le caselle sono riempite
        if (caselleRiempite == MAX_CASELLE) {
            endGame(Giocatore.SCONOSCIUTO);
            return;
        }

        // Il gioco deve continuare, cambia il turno
        setTurno(turno == Giocatore.X ? Giocatore.O : Giocatore.X);
    }

    /**
     * Imposta il turno, aggiornando anche la label delle info
     * @param turno
     */
    private void setTurno(Giocatore turno) {
        this.turno = turno;
        label.setText("Turno di " + turno);
    }

    /**
     * Termina il gioco specificando il giocatore vincitore.
     * 
     * @param vincitore Il giocatore che ha vinto, SCONOSCIUTO se pareggio
     */
    private void endGame(Giocatore vincitore) {
        // Disattiva tutte le caselle
        for (Casella casella : caselle)
            casella.setEnabled(false);

        if (vincitore != Giocatore.SCONOSCIUTO) {
            label.setText(vincitore + " ha vinto!");
        } else {
            label.setText("Pareggio");
        }
    }

    /**
     * Termina il gioco specificando il giocatore vincitore, SCONOSCIUTO se
     * pareggio. Questo metodo colora anche le caselle vincenti.
     * 
     * @param vincitore Il giocatore che ha vinto, SCONOSCIUTO se pareggio
     * @param posizione1 La prima posizione della linea vincente.
     * @param posizione2 La seconda posizione della linea vincente.
     * @param posizione3 La terza posizione della linea vincente.
     */
    private void endGame(Giocatore vincitore, int p1, int p2, int p3) {
        caselle[p1].setBackground(Color.RED);
        caselle[p2].setBackground(Color.RED);
        caselle[p3].setBackground(Color.RED);

        endGame(vincitore);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Casella casella : caselle) {
            if (e.getSource() != casella)
                continue;

            if (casella.getGiocatore() != Giocatore.SCONOSCIUTO)
                continue;

            casella.setGiocatore(turno);
            caselleRiempite++;

            controllaVincitore();
        }
    }

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e){
		System.exit(0);
	}

    @Override
    public void windowDeactivated(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowOpened(WindowEvent e) {}
}
