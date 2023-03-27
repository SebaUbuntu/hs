import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class GUI extends JFrame implements ActionListener, ItemListener {
    private ListaArticoli listaArticoli;
    private ListaClienti listaClienti;

    private JPanel noleggioPanel;
        private JPanel noleggioHeaderPanel;
            private JLabel noleggioHeaderLabel;
            private JSeparator noleggioHeaderSeparator;

        private JPanel noleggioSelezionePanel;
            private JLabel noleggioListaClientiLabel;
            private JComboBox<String> noleggioListaClientiComboBox;
            private JLabel noleggioListaArticoliLabel;
            private JComboBox<String> noleggioListaArticoliComboBox;
            private JButton noleggioNoleggiaArticoloButton;

        private JPanel noleggioInfoPanel;
            private JLabel noleggioInfoClienteLabel;
            private JLabel noleggioInfoArticoloLabel;

    private JPanel restituzionePanel;
        private JPanel restituzioneHeaderPanel;
            private JLabel restituzioneHeaderLabel;
            private JSeparator restituzioneHeaderSeparator;

        private JPanel restituzioneSelezionePanel;
            private JLabel restituzioneListaArticoliLabel;
            private JComboBox<String> restituzioneListaArticoliComboBox;
            private JButton restituzioneRestituisciArticoloButton;

        private JPanel restituzioneInfoPanel;
            private JLabel restituzioneInfoArticoloLabel;
            private JLabel restituzionePrezzoLabel;

    private JPanel infoPanel;
        private JLabel infoLabel;

    public GUI(ListaArticoli listaArticoli, ListaClienti listaClienti) {
        super("GUI");

        this.listaArticoli = listaArticoli;
        this.listaClienti = listaClienti;

        // Noleggio
        noleggioPanel = new JPanel();
        noleggioPanel.setLayout(new GridLayout(3, 1));
            noleggioHeaderPanel = new JPanel();
            noleggioHeaderPanel.setLayout(new GridLayout(2, 1));
                noleggioHeaderLabel = new JLabel("Noleggio");
                noleggioHeaderSeparator = new JSeparator();
            noleggioHeaderPanel.add(noleggioHeaderLabel);
            noleggioHeaderPanel.add(noleggioHeaderSeparator);

            noleggioSelezionePanel = new JPanel();
                noleggioListaClientiLabel = new JLabel("Utente: ");
                noleggioListaClientiComboBox = new JComboBox<String>();
                noleggioListaClientiComboBox.addItemListener(this);
                noleggioListaArticoliLabel = new JLabel("Articolo: ");
                noleggioListaArticoliComboBox = new JComboBox<String>();
                noleggioListaArticoliComboBox.addItemListener(this);
                noleggioNoleggiaArticoloButton = new JButton("Noleggia articolo");
                noleggioNoleggiaArticoloButton.addActionListener(this);
            noleggioSelezionePanel.add(noleggioListaClientiLabel);
            noleggioSelezionePanel.add(noleggioListaClientiComboBox);
            noleggioSelezionePanel.add(noleggioListaArticoliLabel);
            noleggioSelezionePanel.add(noleggioListaArticoliComboBox);
            noleggioSelezionePanel.add(noleggioNoleggiaArticoloButton);

            noleggioInfoPanel = new JPanel();
                noleggioInfoClienteLabel = new JLabel("");
                noleggioInfoArticoloLabel = new JLabel("");
            noleggioInfoPanel.add(noleggioInfoClienteLabel);
            noleggioInfoPanel.add(noleggioInfoArticoloLabel);
        noleggioPanel.add(noleggioHeaderPanel);
        noleggioPanel.add(noleggioSelezionePanel);
        noleggioPanel.add(noleggioInfoPanel);
        // END Noleggio

        // Restituzione
        restituzionePanel = new JPanel();
        restituzionePanel.setLayout(new GridLayout(3, 1));
            restituzioneHeaderPanel = new JPanel();
            restituzioneHeaderPanel.setLayout(new GridLayout(2, 1));
                restituzioneHeaderLabel = new JLabel("Restituzione");
                restituzioneHeaderSeparator = new JSeparator();
            restituzioneHeaderPanel.add(restituzioneHeaderLabel);
            restituzioneHeaderPanel.add(restituzioneHeaderSeparator);

            restituzioneSelezionePanel = new JPanel();
                restituzioneListaArticoliLabel = new JLabel("Articolo: ");
                restituzioneListaArticoliComboBox = new JComboBox<String>();
                restituzioneListaArticoliComboBox.addItemListener(this);
                restituzioneRestituisciArticoloButton = new JButton("Restituisci articolo");
                restituzioneRestituisciArticoloButton.addActionListener(this);
            restituzioneSelezionePanel.add(restituzioneListaArticoliLabel);
            restituzioneSelezionePanel.add(restituzioneListaArticoliComboBox);
            restituzioneSelezionePanel.add(restituzioneRestituisciArticoloButton);

            restituzioneInfoPanel = new JPanel();
                restituzioneInfoArticoloLabel = new JLabel("");
                restituzionePrezzoLabel = new JLabel("");
            restituzioneInfoPanel.add(restituzioneInfoArticoloLabel);
            restituzioneInfoPanel.add(restituzionePrezzoLabel);
        restituzionePanel.add(restituzioneHeaderPanel);
        restituzionePanel.add(restituzioneSelezionePanel);
        restituzionePanel.add(restituzioneInfoPanel);
        // END Restituzione

        // Info
        infoPanel = new JPanel();
            infoLabel = new JLabel("Prezzo medio del noleggio: " + listaArticoli.prezzoMedio());
        infoPanel.add(infoLabel);
        // END Info

        setLayout(new GridLayout(3, 1));
        add(noleggioPanel);
        add(restituzionePanel);
        add(infoPanel);

        updateNoleggioListaArticoli();
        updateNoleggioListaClienti();
        updateNoleggioInfoArticolo();
        updateRestituzioneListaArticoli();
        updateRestituzioneInfoArticolo();
        updateRestituzionePrezzo();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setVisible(true);
    }

    private void updateNoleggioListaClienti() {
        noleggioListaClientiComboBox.removeAllItems();
        for (Cliente cliente : listaClienti.getListaClienti())
            noleggioListaClientiComboBox.addItem(cliente.fullName());
    }

    private void updateNoleggioListaArticoli() {
        noleggioListaArticoliComboBox.removeAllItems();
        for (Articolo articolo : listaArticoli.getListaArticoli(false))
            noleggioListaArticoliComboBox.addItem(articolo.getModello());
    }

    private void updateNoleggioInfoArticolo() {
        int index = noleggioListaArticoliComboBox.getSelectedIndex();
        if (index < 0) {
            noleggioInfoArticoloLabel.setText("");
            return;
        }

        Articolo articolo = listaArticoli.getArticoli(false).get(index);
        noleggioInfoArticoloLabel.setText("Info articolo selezionato: " + articolo.toString());
    }

    private void updateNoleggioInfoCliente() {
        int index = noleggioListaClientiComboBox.getSelectedIndex();
        if (index < 0) {
            noleggioInfoClienteLabel.setText("");
            return;
        }

        Cliente cliente = listaClienti.getCliente(index);
        noleggioInfoClienteLabel.setText("Info cliente selezionato: " + cliente.toString());
    }

    private void updateRestituzioneInfoArticolo() {
        int index = restituzioneListaArticoliComboBox.getSelectedIndex();
        if (index < 0) {
            restituzioneInfoArticoloLabel.setText("");
            return;
        }

        Articolo articolo = listaArticoli.getArticoli(true).get(index);
        restituzioneInfoArticoloLabel.setText("Info articolo selezionato: " + articolo.toString());
    }

    private void updateRestituzioneListaArticoli() {
        restituzioneListaArticoliComboBox.removeAllItems();
        for (Articolo articolo : listaArticoli.getListaArticoli(true))
            restituzioneListaArticoliComboBox.addItem(articolo.getModello());
    }

    private void updateRestituzionePrezzo() {
        double prezzo = 0;

        for (Articolo articolo : listaArticoli.getListaArticoli(true))
            prezzo += articolo.getPrezzoNoleggioGiornaliero();

        restituzionePrezzoLabel.setText("Prezzo: " + prezzo + " euro/h");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == noleggioNoleggiaArticoloButton) {
            int index = noleggioListaArticoliComboBox.getSelectedIndex();
            if (index < 0)
                return;

            Cliente cliente = listaClienti.getCliente(noleggioListaClientiComboBox.getSelectedIndex());
            Articolo articolo = listaArticoli.getArticoli(false).get(index);
            articolo.setNoleggiante(cliente);
        } else if (source == restituzioneRestituisciArticoloButton) {
            int index = restituzioneListaArticoliComboBox.getSelectedIndex();
            if (index < 0)
                return;

            Articolo articolo = listaArticoli.getArticoli(true).get(index);
            articolo.restituisci();
        } else {
            System.out.println("Source sconosciuto");
        }
        updateNoleggioListaArticoli();
        updateRestituzioneListaArticoli();
        updateRestituzionePrezzo();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getSource();

        if (source == noleggioListaClientiComboBox) {
            updateNoleggioInfoCliente();
        } else if (source == noleggioListaArticoliComboBox) {
            updateNoleggioInfoArticolo();
        } else if (source == restituzioneListaArticoliComboBox) {
            updateRestituzioneInfoArticolo();
        } else {
            System.out.println("Source sconosciuto");
        }
    }
}
