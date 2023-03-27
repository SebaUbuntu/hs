using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsUtenti
{
    public class Utente
    {
        private int id;
        private string user;
        private string password;
        private string email;

        public Utente(int id, string user, string password, string email)
        {
            this.id = id;
            this.user = user ?? throw new ArgumentNullException(nameof(user));
            this.password = password ?? throw new ArgumentNullException(nameof(password));
            this.email = email ?? throw new ArgumentNullException(nameof(email));
        }

        public int Id { get => id; set => id = value; }
        public string User { get => user; set => user = value; }
        public string Password { get => password; set => password = value; }
        public string Email { get => email; set => email = value; }
    }
}
