using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsAppFilm
{
    public class Film
    {
        private int id;
        private string titolo;
        private string categoria;

        public Film(int id, string titolo, string categoria)
        {
            this.id = id;
            this.titolo = titolo ?? throw new ArgumentNullException(nameof(titolo));
            this.categoria = categoria ?? throw new ArgumentNullException(nameof(categoria));
        }

        public int Id { get => id; }
        public string Titolo { get => titolo; }
        public string Categoria { get => categoria; }
    }
}
