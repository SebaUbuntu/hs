import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.swing.JLabel;

public class Screen extends JLabel {
    static private ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");

    public Screen() {
        super();

        setHorizontalAlignment(JLabel.CENTER);
        setText("0");
    }

    private boolean isNumber(String s) {
        return s.matches("[0-9]");
    }

    public void append(String s) {
        String currentText = getText();

        if (currentText.equals("Error"))
            return;

        if (currentText.equals("0") && isNumber(s)) {
            setText(s);
            return;
        }

        if (s.equals(".") && currentText.contains(".")) {
            currentText = currentText.replace(".", "");
        }

        setText(currentText + s);
    }

    /**
     * Calcola l'operazione dentro getText() e lo imposta come testo dello Screen
     * Usiamo il metodo eval() di JavaScript per calcolare l'operazione
     * Fonte: https://stackoverflow.com/a/37332202
     */
    public void calculate() {
        String currentText = getText();

        if (currentText.equals("Error"))
            return;

        try {
            setText(engine.eval(currentText).toString());
        } catch (Exception e) {
            setText("Error");
        }
    }

    public void clear() {
        setText("0");
    }
}
