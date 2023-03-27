using System;
using System.Data;

using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;

namespace WindowsFormsDBArte
{
    public partial class FormQuadri : Form
    {
        private DataTable dati;
        private String codice;
        private String titolo;
        private String tabella;
        /// <summary>
        /// Questa form è unica e configurata per vedere i quadri sia di un museo preselezionato o di un artista
        ///
        /// parametri: dati è la DataTable già popolata con quadri selezionati, codice è il codice del museo o artista selezionato, tabella è o museo o artista in base al codice selezionato, titolo il nome del museo o artista selezionato
        /// il costruttore verifica se è stato invocato con tabella 'museo' o 'artista' per predisporre la combobox opportuna
        /// In generale con questa form si possono fare tutte e 3 le modifiche classiche per DataTable/DB: modifica, cancellazione, inserimento
        /// Le combobox servono per l'inserimento di un nuovo quadro: 
        ///    se caricati i dati di museo l'inserimento di un nuovo quadro deve obbligatoriamente inserire nel relativo campo della nuova DataRow il codice (parametro) del museo
        ///    viceversa deve inserire il codice artista.
        ///    Le comboBox devono invece permettere la scelta dell'artista (per il quadro nuovo) se passato il codice museo o scelta del museo se passato l'artista
        ///    Fatta la scelta del valore nella comboBox si può prelevare direttamente il codice artista/museo con comboBoxCodice.SelectedValue
        /// 
        /// </summary>
        /// <param name="dati"></param>
        /// <param name="codice"></param>
        /// <param name="tabella"></param>
        /// <param name="titolo"></param>
        public FormQuadri(DataTable dati, string codice, string tabella, string titolo)
        {
            this.dati = dati;
            this.codice = codice;
            this.titolo = titolo;
            this.tabella = tabella;
            InitializeComponent();
            
            dataGridViewQuadri.DataSource = dati;

            ClassMySqlDati cmsd = new ClassMySqlDati();
            if (tabella == "museo")
            {
                dataGridViewQuadri.Columns["QQ_CodiceMuseo"].Visible = false;
                comboBoxCodice.DataSource = cmsd.GetTabellaArtisti();
                comboBoxCodice.DisplayMember = "AR_Nome";
                comboBoxCodice.ValueMember = "AR_CodiceArtista";
            }
            else
            {
                dataGridViewQuadri.Columns["QQ_CodiceArtista"].Visible = false;
                comboBoxCodice.DataSource = cmsd.GetTabellaMusei();
                comboBoxCodice.DisplayMember = "MM_Nome";
                comboBoxCodice.ValueMember = "MM_CodiceMuseo";
            }
        }

        private void FormQuadri_Load(object sender, EventArgs e)
        {
            labelTitolo.Text = titolo + $"     codice: {codice}";
        }

        /// <summary>
        /// Richiama il metodo dtDati.UpdateTabellaQuadri che è stato configurato per eseguire tutte 3 le modifiche (insert, update, delete) automaticamente
        /// Verificando quelle fatte dall'utente nella dataGridView/dataTable
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonUpdateQuadri_Click(object sender, EventArgs e)
        {
            ClassMySqlDati dtDati = new ClassMySqlDati();
            if (textBoxTitolo.Text.Length > 0)
            {
                DataRow riga = dati.NewRow();
                riga["QQ_Altezza"] =  textBoxAltezza.Text;
                riga["QQ_Larghezza"] = textBoxLarghezza.Text;
                riga["QQ_TitoloQuadro"] = textBoxTitolo.Text;
                riga["QQ_Tecnica"] = textBoxTecnica.Text;
                riga["QQ_Note"] = textBoxNote.Text;
                riga["QQ_AnnoEsecuzione"] = textBoxAnno.Text;
                if (tabella == "museo")
                {
                    riga["QQ_CodiceMuseo"] = codice;
                    riga["QQ_CodiceArtista"] =  comboBoxCodice.SelectedValue;
                }
                else
                {
                    riga["QQ_CodiceMuseo"] = comboBoxCodice.SelectedValue;
                    riga["QQ_CodiceArtista"] =  codice;
                }
                dati.Rows.Add(riga);
            }
            try
            {
                dtDati.UpdateTabellaQuadri(dati);
                MessageBox.Show("Dati salvati !");
            }
            catch (Exception ex) { MessageBox.Show(ex.Message); }
        }
    }
}
