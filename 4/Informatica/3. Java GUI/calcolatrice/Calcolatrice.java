import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Calcolatrice implements ActionListener {
    static private final String NAME = "Calcolatrice";

    static private final int WINDOW_WIDTH = 250;
    static private final int WINDOW_HEIGHT = 350;

    static private final Font FONT = new Font("Arial", Font.PLAIN, 18);

    private JFrame frame;

    private JPanel inputLabelPanel;
    private JPanel numbersPanel;
    private JPanel operatorsPanel;

    private Screen screen;

    private JButton[] numberButtons;

    private JButton divButton;
    private JButton mulButton;
    private JButton subButton;
    private JButton addButton;
    private JButton commaButton;
    private JButton moduloButton;
    private JButton equalButton;
    private JButton clearButton;

    public Calcolatrice() {
        frame = new JFrame(NAME);

        inputLabelPanel = new JPanel();
        numbersPanel = new JPanel();
        operatorsPanel = new JPanel();

        screen = new Screen();

        numberButtons = new JButton[16];

        divButton = new JButton("÷");
        mulButton = new JButton("×");
        subButton = new JButton("-");
        addButton = new JButton("+");
        commaButton = new JButton(",");
        moduloButton = new JButton("%");
        equalButton = new JButton("=");
        clearButton = new JButton("C");

        frame.setTitle(NAME);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        for (int i = 0; i < numberButtons.length; i++)
            numberButtons[i] = new JButton("" + i);

        inputLabelPanel.add(screen, BorderLayout.CENTER);

        numbersPanel.setLayout(new GridLayout(4, 4));
        for (JButton button : new JButton[] {
            numberButtons[7], numberButtons[8], numberButtons[9],
            numberButtons[4], numberButtons[5], numberButtons[6],
            numberButtons[1], numberButtons[2], numberButtons[3],
            numberButtons[0], commaButton,      moduloButton,
        }) {
            button.addActionListener(this);
            button.setFont(FONT);
            numbersPanel.add(button);
        }

        operatorsPanel.setLayout(new GridLayout(4, 2));
        for (JButton button : new JButton[] {
            divButton, clearButton,
            mulButton, new JButton(),
            subButton, new JButton(),
            addButton, equalButton,
        }) {
            button.addActionListener(this);
            button.setFont(FONT);
            operatorsPanel.add(button);
        }

        frame.add(inputLabelPanel, BorderLayout.NORTH);
        frame.add(numbersPanel, BorderLayout.CENTER);
        frame.add(operatorsPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        for (JButton button : numberButtons) {
            if (source != button)
                continue;

            screen.append(button.getText());
        }

        if (source == divButton) {
            screen.append("/");
        } else if (source == mulButton) {
            screen.append("*");
        } else if (source == subButton) {
            screen.append("-");
        } else if (source == addButton) {
            screen.append("+");
        } else if (source == commaButton) {
            screen.append(".");
        } else if (source == moduloButton) {
            screen.append("%");
        } else if (source == equalButton) {
            screen.calculate();
        } else if (source == clearButton) {
            screen.clear();
        }
    }
}
