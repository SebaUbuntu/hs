using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsAppFilm
{
    public class Dati
    {
        private string[] arrayCategorie = {
            "Comico",
            "Drammatico",
            "Animazione",
        };
        private Film[] arrayFilm = {
            new Film(190290, "The Mission", "Drammatico"),
            new Film(2903123, "Up", "Animazione"),
        };

        public Dati()
        {
        }

        public DataTable DTFilm
        {
            get
            {
                DataTable dt = new DataTable();

                dt.TableName = "Film";

                dt.Columns.Add("ID");
                dt.Columns.Add("Titolo");
                dt.Columns.Add("Categoria");

                DataRow dr;
                foreach (var film in arrayFilm)
                {
                    dr = dt.NewRow();

                    dr[0] = film.Id;
                    dr[1] = film.Titolo;
                    dr[2] = film.Categoria;

                    dt.Rows.Add(dr);
                }

                return dt;
            }
        }

        public string[] ArrayCategorie { get => arrayCategorie; }
    }
}
