using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsDBArte
{
    public partial class FormMain : Form
    {
        public FormMain()
        {
            InitializeComponent();
        }
        private void FormMain_Load(object sender, EventArgs e)
        {
            // In ordine: Test se MySql è avviato; verifica se c'è il DB/Dati, eventualmente crea e popola DB; popola le combo con i dati
            ClassMySqlDati dati = new ClassMySqlDati();
            if (!dati.isStartedMysql())
            {
                MessageBox.Show("MySql sembra non essere avviato! Provvedi e riprova", "OKKIO", MessageBoxButtons.OK, MessageBoxIcon.Error);
                Close();
            }
            else
                try
                {
                    PopolaCombo();
                }
                catch (MySql.Data.MySqlClient.MySqlException err)
                {
                    MessageBox.Show(err.Message + ". Clicca OK e crea/popola DB!", "OKKIO", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    dati.CreaDB_Dati();
                    PopolaCombo();
                }
        }
        private void PopolaCombo()
        {
            ClassMySqlDati dati = new ClassMySqlDati();
            comboBoxArtisti.DataSource = dati.GetListaArtisti();
            comboBoxMusei.DataSource = dati.GetListaMusei();
        }
        private void buttonVediOpereMuseo_Click(object sender, EventArgs e)
        {
            ClassMySqlDati dati = new ClassMySqlDati();
            String cod = dati.GetCodiceMuseoConNome(comboBoxMusei.Text);
            FormQuadri fquadri = new FormQuadri(dati.GetListaQuadriDaCodiceMuseo(cod), cod, "museo", "Quadri del museo: " + comboBoxMusei.Text);
            fquadri.Show();
        }

        private void buttonVediOpereArtisti_Click(object sender, EventArgs e)
        {
            ClassMySqlDati dati = new ClassMySqlDati();
            String cod = dati.GetCodiceArtistaConNome(comboBoxArtisti.Text);
            FormQuadri fquadri = new FormQuadri(dati.GetListaQuadriDaCodiceArtista(cod), cod, "artista", "Quadri dell'artista: " + comboBoxArtisti.Text);
            fquadri.Show();
        }
    }
}
