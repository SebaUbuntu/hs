using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsAppVerifica2
{
    public partial class FormNegozioMultimediale : Form
    {
        private Negozio negozio;

        public FormNegozioMultimediale()
        {
            negozio = new Negozio();

            InitializeComponent();
        }

        private void FormNegozioMultimediale_Load(object sender, EventArgs e)
        {
            ReloadDataSource();
        }

        private void buttonOrdinaPerCosto_Click(object sender, EventArgs e)
        {
            negozio.OrdinamentoPerCosto();
            ReloadDataSource();
        }

        private void buttonOrdinaPerTitolo_Click(object sender, EventArgs e)
        {
            negozio.OrdinamentoPerTitolo();
            ReloadDataSource();
        }

        private void ReloadDataSource()
        {
            dataGridViewNegozio.DataSource = negozio.DTArticoli;
        }
    }
}
