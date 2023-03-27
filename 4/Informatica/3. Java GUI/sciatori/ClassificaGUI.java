import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class ClassificaGUI implements ActionListener, ItemListener {
    static private String NAME = "Classifica";

    private DatiSciatori datiSciatori;

    private JFrame frame;

    private JPanel sciatoreInfoPanel;
    private String[] sciatoreSelectorComboBoxStrings;
    private JComboBox<String> sciatoreSelectorComboBox;
    private JLabel sciatoreNomeLabel;
    private JLabel sciatoreCognomeLabel;
    private JLabel sciatorePosizionePrimaMancheLabel;
    private JLabel sciatorePosizioneSecondaMancheLabel;
    private JButton sciatoreInfoButton;

    private JPanel vincitorePanel;
    private JLabel vincitoreLabel;
    private JTextArea vincitoreClassificaTextArea;
    private ButtonGroup vincitoreMancheButtonGroup;
    private JRadioButton vincitoreManche1RadioButton;
    private JRadioButton vincitoreManche2RadioButton;

    public ClassificaGUI() {
        datiSciatori = new DatiSciatori();
        datiSciatori.aggiungiSciatore(new Sciatori("Peppe", "Fetish"));
        datiSciatori.aggiungiSciatore(new Sciatori("Il", "Puma"));
        datiSciatori.aggiungiSciatore(new Sciatori("Andrea", "Diprè"));
        datiSciatori.aggiungiSciatore(new Sciatori("Romelu", "Lakaka"));
        datiSciatori.aggiungiSciatore(new Sciatori("Alvaro", "Matata"));

        sciatoreSelectorComboBoxStrings = new String[datiSciatori.size()];
        for (int i = 0; i < datiSciatori.size(); i++)
            sciatoreSelectorComboBoxStrings[i] = datiSciatori.trovaSciatore(i).fullName();

        frame = new JFrame(NAME);
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sciatoreInfoPanel = new JPanel();
        sciatoreInfoPanel.setLayout(new GridLayout(5, 1));

        sciatoreSelectorComboBox = new JComboBox<String>(sciatoreSelectorComboBoxStrings);
        sciatoreSelectorComboBox.addItemListener(this);

        sciatoreNomeLabel = new JLabel();
        sciatoreCognomeLabel = new JLabel();
        sciatorePosizionePrimaMancheLabel = new JLabel();
        sciatorePosizioneSecondaMancheLabel = new JLabel();
        setSciatoreData(datiSciatori.trovaSciatore(0));

        sciatoreInfoPanel.add(sciatoreSelectorComboBox);
        sciatoreInfoPanel.add(sciatoreNomeLabel);
        sciatoreInfoPanel.add(sciatoreCognomeLabel);
        sciatoreInfoPanel.add(sciatorePosizionePrimaMancheLabel);
        sciatoreInfoPanel.add(sciatorePosizioneSecondaMancheLabel);

        vincitorePanel = new JPanel();
        vincitorePanel.setLayout(new GridLayout(5, 1));

        vincitoreLabel = new JLabel("Il vincitore è " + datiSciatori.vincitore().fullName());

        vincitoreClassificaTextArea = new JTextArea();
        vincitoreClassificaTextArea.setSize(100, 100);
        setClassifica(true);

        vincitoreMancheButtonGroup = new ButtonGroup();
        vincitoreManche1RadioButton = new JRadioButton("Manche 1");
        vincitoreManche2RadioButton = new JRadioButton("Manche 2");
        vincitoreMancheButtonGroup.add(vincitoreManche1RadioButton);
        vincitoreMancheButtonGroup.add(vincitoreManche2RadioButton);
        vincitoreManche1RadioButton.addItemListener(this);
        vincitoreManche2RadioButton.addItemListener(this);

        sciatoreInfoButton = new JButton("Info");
        sciatoreInfoButton.addActionListener(this);

        vincitorePanel.add(vincitoreLabel);
        vincitorePanel.add(vincitoreClassificaTextArea);
        vincitorePanel.add(vincitoreManche1RadioButton);
        vincitorePanel.add(vincitoreManche2RadioButton);
        vincitorePanel.add(sciatoreInfoButton);

        frame.add(sciatoreInfoPanel, "West");
        frame.add(vincitorePanel, "Center");
        frame.setVisible(true);
    }

    private void setSciatoreData(Sciatori sciatore) {
        sciatoreNomeLabel.setText("Nome: " + sciatore.getNome());
        sciatoreCognomeLabel.setText("Cognome: " + sciatore.getCognome());
        sciatorePosizionePrimaMancheLabel.setText("Prima manche: " + datiSciatori.getPosizione(sciatore, true) + "°");
        sciatorePosizioneSecondaMancheLabel.setText("Seconda manche: "+ datiSciatori.getPosizione(sciatore, false) + "°");
    }

    private void setClassifica(boolean primaManche) {
        String[] sciatori_ordered = new String[datiSciatori.size()];

        for (int i = 0; i < datiSciatori.size(); i++) {
            Sciatori sciatore = datiSciatori.trovaSciatore(i);
            sciatori_ordered[datiSciatori.getPosizione(sciatore, primaManche) - 1] = sciatore.fullName();
        }

        vincitoreClassificaTextArea.setText("");
        for (int i = 0; i < sciatori_ordered.length; i++) {
            vincitoreClassificaTextArea.append((i + 1) + "° " + sciatori_ordered[i] + "\n");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == sciatoreInfoButton) {
            if (vincitoreManche1RadioButton.isSelected()) {
                new SciatoriGUI(datiSciatori.getSciatori(), true);
            }
            else if (vincitoreManche2RadioButton.isSelected()) {
                new SciatoriGUI(datiSciatori.getSciatori(), false);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED)
            return;

        String nome = ((String) sciatoreSelectorComboBox.getSelectedItem()).split(" ")[0];
        Sciatori sciatore = datiSciatori.trovaSciatore(nome);
        if (sciatore != null)
            setSciatoreData(sciatore);
    }
}
