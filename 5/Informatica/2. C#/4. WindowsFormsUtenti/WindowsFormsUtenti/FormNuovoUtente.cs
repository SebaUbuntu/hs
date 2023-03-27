using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsUtenti
{
    public partial class FormNuovoUtente : Form
    {
        public FormNuovoUtente()
        {
            InitializeComponent();
        }

        private void buttonSalva_Click(object sender, EventArgs e)
        {
            var nome = textBoxNome.Text.Trim();
            var cognome = textBoxCognome.Text.Trim();

            if (nome == "" || cognome == "")
            {
                return;
            }

            Dati.Register(nome, cognome);

            MessageBox.Show("Utente registrato");
            Close();
        }
    }
}
