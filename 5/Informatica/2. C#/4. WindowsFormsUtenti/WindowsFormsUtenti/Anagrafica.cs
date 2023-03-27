using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsUtenti
{
    public class Anagrafica
    {
        private int id;
        private string cognome;
        private string nome;

        public Anagrafica(int id, string cognome, string nome)
        {
            this.id = id;
            this.cognome = cognome ?? throw new ArgumentNullException(nameof(cognome));
            this.nome = nome ?? throw new ArgumentNullException(nameof(nome));
        }

        public int Id { get => id; set => id = value; }
        public string Cognome { get => cognome; set => cognome = value; }
        public string Nome { get => nome; set => nome = value; }
    }
}
