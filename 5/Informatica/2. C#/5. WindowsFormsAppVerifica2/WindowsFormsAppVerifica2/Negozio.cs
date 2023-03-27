using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsAppVerifica2
{
    public class Negozio
    {
        private List<Articolo> articoli;

        public Negozio()
        {
            articoli = new List<Articolo>();

            articoli.Add(new CD(12345, "A Kind Of Magic", 30.00, 12));
            articoli.Add(new CD(23456, "The Miracle", 30.00, 6));
            articoli.Add(new CD(34567, "Made in Heaven", 30.00, 15));

            articoli.Add(new DVD(45678, "La vita è bella", 20.00, 120));
            articoli.Add(new DVD(56789, "Jumanji", 25.00, 180));
            articoli.Add(new DVD(67890, "Fast and Furious 7", 30.00, 90));
        }

        public List<Articolo> Articoli { get => articoli; }

        public override string ToString()
        {
            var s = "Articoli: ";

            foreach (var articolo in Articoli)
            {
                s += articolo + ", ";
            }

            return s;
        }

        public void SetDurataDVD(int durata)
        {
            foreach (var articolo in Articoli)
            {
                if (articolo is DVD dvd)
                {
                    Console.WriteLine("DVD");
                    dvd.Durata = durata;
                }
            }
        }

        public List<Articolo> RicercaPerTitolo(string titolo)
        {
            var lista = new List<Articolo>();

            foreach (var articolo in Articoli)
            {
                if (articolo.Titolo == titolo)
                {
                    lista.Add(articolo);
                }
            }

            return lista;
        }

        public DataTable DTArticoli
        {
            get
            {
                var dt = new DataTable();

                dt.TableName = "Articoli";

                dt.Columns.Add("Codice");
                dt.Columns.Add("Tipo");
                dt.Columns.Add("Titolo");
                dt.Columns.Add("Costo");

                foreach (var articolo in Articoli)
                {
                    var dr = dt.NewRow();

                    dr[0] = articolo.Codice;
                    dr[1] = articolo.GetType().Name;
                    dr[2] = articolo.Titolo;
                    dr[3] = articolo.Costo;

                    dt.Rows.Add(dr);
                }

                return dt;
            }
        }

        public void OrdinamentoPerCosto()
        {
            Articoli.Sort(new Articolo.OrdinaPerCosto());
        }

        public void OrdinamentoPerTitolo()
        {
            Articoli.Sort(new Articolo.OrdinaPerTitolo());
        }
    }
}
