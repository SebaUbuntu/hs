import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class Casella extends JButton {
    static private final Color COLORE_X = Color.red;
    static private final Color COLORE_O = Color.blue;

    static private final Font FONT = new Font("Arial", Font.BOLD, 100);

    public Casella() {
        setFont(FONT);
        setFocusable(false);
    }

    public Giocatore getGiocatore() {
        String text = getText();
        if (text.equals(Giocatore.X.toString()))
            return Giocatore.X;
        if (text.equals(Giocatore.O.toString()))
            return Giocatore.O;
        return Giocatore.SCONOSCIUTO;
    }

    public void setGiocatore(Giocatore giocatore) {
        setText(giocatore.toString());
        setForeground(giocatore == Giocatore.X ? COLORE_X : COLORE_O);
    }
}
