using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsAppVerifica2
{
    public class DVD : Articolo
    {
        private int durata;

        public DVD() : base()
        {
        }

        public DVD(int codice, string titolo, double costo, int durata) : base(codice, titolo, costo)
        {
            this.Durata = durata;
        }

        public int Durata { get => durata; set => durata = value; }

        public override string ToString()
        {
            return base.ToString()
                + ", durata: " + Durata;
        }
    }
}
