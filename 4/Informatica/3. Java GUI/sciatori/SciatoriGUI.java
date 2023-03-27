import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class SciatoriGUI {
    private Sciatori[] array;
    private JFrame classifica;

    private JTextArea tableTextArea;
    private DecimalFormat formatter = new DecimalFormat("#.####");

    public SciatoriGUI(ArrayList<Sciatori> sciatori, boolean primaManche) {
        array = sciatori.toArray(new Sciatori[sciatori.size()]);

        classifica = new JFrame("Classifica");
        classifica.setSize(430, 220);

        tableTextArea = new JTextArea();
        updateTable(primaManche);

        classifica.add(tableTextArea, "Center");

        classifica.setVisible(true); 
    }

    private void updateTable(boolean primaManche) {
        tableTextArea.setText("Posizione Nome Cognome Tempo\n\n");
        sort(primaManche);
        for (int i = 0; i < array.length; i++) {
            tableTextArea.append((i+1) + "° " + array[i].fullName() + " " + formatter.format(array[i].getTempoPrimaManche()) + " " + formatter.format(array[i].getTempoSecondaManche()) + "\n\n");
        }
    }

    private void sort(boolean primaManche) {
        boolean ordinato = false;
        Sciatori temp;
        int count;

        for (int i = 0; i < (array.length-1) && !ordinato; i++) {
            count = 0;
            for (int j = 0; j < (array.length-1); j++) {
                double tempo = primaManche
                        ? array[j].getTempoPrimaManche()
                        : array[j].getTempoSecondaManche();
                double tempo2 = primaManche
                        ? array[j+1].getTempoPrimaManche()
                        : array[j+1].getTempoSecondaManche();
                if (tempo > tempo2) {
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    count++;
                }
            }
            if (count == 0)
                ordinato = true;
        }
    }
}
