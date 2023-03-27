using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsAppProva
{
    public partial class FormInizio : Form
    {
        public FormInizio()
        {
            InitializeComponent();

            comboBoxClassi.DataSource = new ClassData().Classi;
        }

        private void buttonPopolaCombo_Click(object sender, EventArgs e)
        {
            var trimmedString = textBoxStudente.Text.Trim();
            popolaCombo(trimmedString);
            textBoxStudenti.Text += trimmedString + Environment.NewLine;
        }

        private void popolaCombo(string s)
        {
            if (!comboBoxStudenti.Items.Contains(s))
            {
                comboBoxStudenti.Items.Add(s);
            }
        }
    }
}
