using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsAppVerifica2
{
    public class Articolo
    {
        private int codice;
        private string titolo;
        private double costo;

        public Articolo()
        {
        }

        public Articolo(int codice, string titolo, double costo)
        {
            this.Codice = codice;
            this.Titolo = titolo ?? throw new ArgumentNullException(nameof(titolo));
            this.Costo = costo;
        }

        public int Codice { get => codice; set => codice = value; }
        public string Titolo { get => titolo; set => titolo = value ?? throw new ArgumentNullException(nameof(titolo)); }
        public double Costo { get => costo; set => costo = value; }

        public override string ToString()
        {
            return "Codice: " + Codice
                + ", titolo: " + Titolo
                + ", costo" + Costo;
        }

        public class OrdinaPerCosto : IComparer<Articolo>
        {
            public int Compare(Articolo x, Articolo y)
            {
                return x.Costo.CompareTo(y.Costo);
            }
        }

        public class OrdinaPerTitolo : IComparer<Articolo>
        {
            public int Compare(Articolo x, Articolo y)
            {
                return x.Titolo.CompareTo(y.Titolo);
            }
        }
    }
}
