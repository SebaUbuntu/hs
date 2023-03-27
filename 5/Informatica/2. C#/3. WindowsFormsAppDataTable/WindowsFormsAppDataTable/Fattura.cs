using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsAppDataTable
{
    public class Fattura
    {
        private int idCliente;
        private double importo;

        public Fattura(int idCliente, double importo)
        {
            this.idCliente = idCliente;
            this.importo = importo;
        }

        public int IdCliente { get => idCliente; }
        public double Importo { get => importo; }
    }
}
