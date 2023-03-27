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
    public partial class WindowsFormFilm : Form
    {
        private Dati dati;
        private string categoria;

        public WindowsFormFilm(Dati dati, string categoria)
        {
            InitializeComponent();
            
            this.dati = dati;
            this.categoria = categoria;
        }

        private void WindowsFormFilm_Load(object sender, EventArgs e)
        {
            EnumerableRowCollection<DataRow> filteredEnumerable = dati.DTFilm.AsEnumerable().Where(r => r.Field<string>("Categoria") == categoria);
            if (filteredEnumerable.Any())
            {
                dataGridViewFilm.DataSource = filteredEnumerable.CopyToDataTable();
            }
        }
    }
}
