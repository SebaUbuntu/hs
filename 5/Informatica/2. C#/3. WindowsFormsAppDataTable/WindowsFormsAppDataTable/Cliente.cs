using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsAppDataTable
{
    public class Cliente
    {
        private int idCliente;
        private string cognome;
        private string nome;

        public Cliente(int idCliente, string cognome, string nome)
        {
            this.idCliente = idCliente;
            this.cognome = cognome ?? throw new ArgumentNullException(nameof(cognome));
            this.nome = nome ?? throw new ArgumentNullException(nameof(nome));
        }

        public int IdCliente { get => idCliente; }
        public string Cognome { get => cognome; }
        public string Nome { get => nome; }
    }
}
