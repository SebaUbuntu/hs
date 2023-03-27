using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsAppDataTable
{
    public partial class WindowsFormClientiFatture : Form
    {
        public WindowsFormClientiFatture()
        {
            InitializeComponent();

            dataGridViewClienti.DataSource = Dati.DTClienti;
        }

        private void buttonMostraFatture_Click(object sender, EventArgs e)
        {
            var selectedCells = dataGridViewClienti.SelectedCells;

            var firstRow = selectedCells[0].OwningRow;
            var clienteId = int.Parse(firstRow.Cells[0].Value.ToString());
            dataGridViewFatture.DataSource = Dati.GetFattureDataTable(clienteId);
        }
    }
}
