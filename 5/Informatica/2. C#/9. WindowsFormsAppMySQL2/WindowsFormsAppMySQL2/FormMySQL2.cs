using MySqlX.XDevAPI;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsAppMySQL2
{
    public partial class FormMySQL2 : Form
    {
        private Database database;

        public FormMySQL2()
        {
            InitializeComponent();
        }

        private void FormMySQL2_Load(object sender, EventArgs e)
        {
            database = new Database();
            //dataGridView.DataSource = database.GetFilm(2011, 0);
        }

        private void buttonUpdate_Click(object sender, EventArgs e)
        {
            database.UpdateFilm((DataTable)dataGridView.DataSource);
        }

        private void buttonGetFilm_Click(object sender, EventArgs e)
        {
            CaricaFilm();
        }

        private void CaricaFilm()
        {
            dataGridView.DataSource = database.GetFilm(Int32.Parse(textBoxYear.Text)/*, Int32.Parse(comboBoxGenere.Text)*/);
        }
    }
}
