using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace SqlCommandDemo
{
    public partial class FormStart : Form
    {
        public FormStart()
        {
            InitializeComponent();
        }

        private void buttonCreaDB_Click(object sender, EventArgs e)
        {
            ClassSqlCommandDemo c = new ClassSqlCommandDemo();
            c.CreaProva();
            c.CreaTabelle();
        }

        private void buttonPopola_Click(object sender, EventArgs e)
        {
            ClassSqlCommandDemo c = new ClassSqlCommandDemo();
            c.Popola();
        }

        private void buttonCarica_Click(object sender, EventArgs e)
        {
            ClassSqlCommandDemo c = new ClassSqlCommandDemo();
            dataGridViewProva.DataSource = c.tabellaClienti();
        }

        private void buttonSalva_Click(object sender, EventArgs e)
        {
            DataTable tabellaClienti = (DataTable) dataGridViewProva.DataSource;
            string s = "Clienti:\n";
            foreach (DataRow riga in tabellaClienti.Rows)
                s += riga["nome"].ToString() + "\n";
            
            MessageBox.Show(s);

            foreach (DataRow riga in tabellaClienti.Rows)
            {
                if (riga.RowState == DataRowState.Modified)
                    MessageBox.Show(riga["nome"].ToString() + "  è stato modificato !");
            }
            // Aggiungere SQL Update
        }

        private void buttonDatiConParametri_Click(object sender, EventArgs e)
        {
            ClassSqlCommandDemo c = new ClassSqlCommandDemo();
            dataGridViewProva.DataSource = c.tabellaClientiFatture(textBoxNome.Text);
        }

        private void buttonDatiConFunzione_Click(object sender, EventArgs e)
        {
            ClassSqlCommandDemo c = new ClassSqlCommandDemo();
            dataGridViewProva.DataSource = c.tabellaClientiFattureConFunction(textBoxNome.Text);
            labelTotale.Text = "Totale Importo: " + c.totaleFatturePerCliente(textBoxNome.Text).ToString();
        }

        private void buttonCancellaDati_Click(object sender, EventArgs e)
        {
            ClassSqlCommandDemo c = new ClassSqlCommandDemo();
            c.cancellaRecord();
        }

        private void buttonCreaDBMySql_Click(object sender, EventArgs e)
        {
            ClassSqlCommandDemoMySql c = new ClassSqlCommandDemoMySql();
            c.CreaProva();
            c.CreaTabelle();
        }

        private void buttonPopolaMySql_Click(object sender, EventArgs e)
        {
            ClassSqlCommandDemoMySql c = new ClassSqlCommandDemoMySql();
            c.PopolaClienti();
        }

        private void buttonMostraDatiMySql_Click(object sender, EventArgs e)
        {
            ClassSqlCommandDemoMySql c = new ClassSqlCommandDemoMySql();
            dataGridViewProva.DataSource = c.GetTabellaClienti();
        }

        private void buttonSalvaDatiMySql_Click(object sender, EventArgs e)
        {
            DataTable tabellaClienti = (DataTable)dataGridViewProva.DataSource;

            foreach (DataRow riga in tabellaClienti.Rows)
            {
                if (riga.RowState == DataRowState.Modified)
                    MessageBox.Show(riga["nome"].ToString() + "  è stato modificato !");
            }
            ClassSqlCommandDemoMySql c = new ClassSqlCommandDemoMySql();
            c.UpdateTabellaClienti(tabellaClienti);
        }

        private void buttonDatiConParametriMySql_Click(object sender, EventArgs e)
        {
            ClassSqlCommandDemoMySql c = new ClassSqlCommandDemoMySql();
            dataGridViewProva.DataSource = c.GetTabellaClientiFatture(textBoxNomeMySql.Text);
        }

        private void buttonCancellaDatiMySql_Click(object sender, EventArgs e)
        {
            ClassSqlCommandDemoMySql c = new ClassSqlCommandDemoMySql();
            c.CancellaRecord();
        }

        private void buttonCreaUtente_Click(object sender, EventArgs e)
        {
            ClassSqlCommandDemoMySql c = new ClassSqlCommandDemoMySql();
            c.CreaUtenteProva();
        }

        private void buttonSelectConUtenteProva_Click(object sender, EventArgs e)
        {
            ClassSqlCommandDemoMySql c = new ClassSqlCommandDemoMySql();
            dataGridViewProva.DataSource = c.GetTabellaClientiConUtenteProva();
        }

        private void buttonAssegnaDiritti_Click(object sender, EventArgs e)
        {
            ClassSqlCommandDemoMySql c = new ClassSqlCommandDemoMySql();
            c.AssegnaDirittiAUtenteProva(true);
        }

        private void buttonRevocaDiritti_Click(object sender, EventArgs e)
        {
            ClassSqlCommandDemoMySql c = new ClassSqlCommandDemoMySql();
            c.AssegnaDirittiAUtenteProva(false);
        }
    }
}
