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
    public partial class FormUtenti : Form
    {
        public FormUtenti()
        {
            InitializeComponent();
        }

        private void FormUtenti_Load(object sender, EventArgs e)
        {
            ReloadData();
        }

        private void ReloadData()
        {
            dataGridViewUtenti.DataSource = Dati.DTUtenti;
        }

        private void buttonLogin_Click(object sender, EventArgs e)
        {
            new FormLogin().Show();
        }

        private void buttonNuovoUtente_Click(object sender, EventArgs e)
        {
            new FormNuovoUtente().ShowDialog();
            ReloadData();
        }
    }
}
