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
    public partial class FormLogin : Form
    {
        public FormLogin()
        {
            InitializeComponent();
        }

        private void buttonLogin_Click(object sender, EventArgs e)
        {
            Utente utente;

            try
            {
                utente = Dati.Login(textBoxUser.Text, textBoxPassword.Text);
            }
            catch (Exception exception)
            {
                MessageBox.Show(exception.Message);
                return;
            }

            var anagrafica = Dati.FindAnagrafica(utente);

            MessageBox.Show(
                "Nome: " + anagrafica.Nome + "\n" +
                "Cognome: " + anagrafica.Cognome + "\n" +
                "Email: " + utente.Email
            );
        }
    }
}
