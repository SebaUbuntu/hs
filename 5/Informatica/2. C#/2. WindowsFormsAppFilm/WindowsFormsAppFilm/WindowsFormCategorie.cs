using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsAppFilm
{
    public partial class WindowsFormCategorie : Form
    {
        private Dati dati;
        public WindowsFormCategorie()
        {
            InitializeComponent();

            dati = new Dati();
        }

        private void WindowsFormCategorie_Load(object sender, EventArgs e)
        {
            comboBoxCategorie.DataSource = dati.ArrayCategorie;
        }

        private void buttonOpenCategoria_Click(object sender, EventArgs e)
        {
            new WindowsFormFilm(dati, (string)comboBoxCategorie.SelectedItem).Show();
        }
    }
}
