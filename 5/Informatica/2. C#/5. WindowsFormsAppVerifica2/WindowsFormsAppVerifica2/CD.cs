using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsAppVerifica2
{
    public class CD : Articolo
    {
        private int numTracce;

        public CD() : base()
        {
        }

        public CD(int codice, string titolo, double costo, int numTracce) : base(codice, titolo, costo)
        {
            this.NumTracce = numTracce;
        }

        public int NumTracce { get => numTracce; set => numTracce = value; }

        public override string ToString()
        {
            return base.ToString()
                + ", numero tracce: " + NumTracce;
        }
    }
}
