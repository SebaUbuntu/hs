using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsAppProva
{
    class ClassData
    {
        private List<String> classi;

        public ClassData()
        {
            Classi = new List<string>();
            Classi.Add("3ainf");
            Classi.Add("3binf");
            Classi.Add("3cinf");
        }

        public List<string> Classi { get => classi; set => classi = value; }
    }
}
