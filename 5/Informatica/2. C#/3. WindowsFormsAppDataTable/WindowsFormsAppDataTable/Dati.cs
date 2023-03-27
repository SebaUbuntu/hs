using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsAppDataTable
{
    public class Dati
    {
        private static Cliente[] clienti =
        {
            new Cliente(190290, "Bianchi", "Mirko"),
            new Cliente(193460, "Verdi", "Marco"),
        };
        private static Fattura[] fatture =
        {
            new Fattura(190290, 1210.50),
            new Fattura(193460, 651.50),
        };

        public static DataTable GetFattureDataTable(int idCliente)
        {
            var fattureCliente = new List<Fattura>();

            foreach (var fattura in fatture)
            {
                if (fattura.IdCliente != idCliente)
                {
                    continue;
                }

                fattureCliente.Add(fattura);
            }

            DataTable dt = new DataTable();

            dt.TableName = "Clienti"; // Non strettamente necessario

            dt.Columns.Add("ID cliente");
            dt.Columns.Add("Importo");

            DataRow dr;
            foreach (var fattura in fattureCliente)
            {
                dr = dt.NewRow();

                dr[0] = fattura.IdCliente;
                dr[1] = fattura.Importo;

                dt.Rows.Add(dr);
            }

            dr = dt.NewRow();

            dr[0] = "Totale";
            dr[1] = fattureCliente.Sum(item => item.Importo);

            dt.Rows.Add(dr);

            dr = dt.NewRow();

            dr[0] = "Media";
            dr[1] = fattureCliente.Sum(item => item.Importo) / fattureCliente.Count;

            dt.Rows.Add(dr);

            return dt;
        }

        public static DataTable DTClienti
        {
            get
            {
                DataTable dt = new DataTable();

                dt.TableName = "Clienti"; // Non strettamente necessario

                dt.Columns.Add("ID");
                dt.Columns.Add("Cognome");
                dt.Columns.Add("Nome");

                DataRow dr;
                foreach (var cliente in clienti)
                {
                    dr = dt.NewRow();

                    dr[0] = cliente.IdCliente;
                    dr[1] = cliente.Cognome;
                    dr[2] = cliente.Nome;

                    dt.Rows.Add(dr);
                }

                return dt;
            }
        }
    }
}
