using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsUtenti
{
    public class Dati
    {
        private static List<Anagrafica> anagrafiche = new List<Anagrafica> {
            new Anagrafica(190290, "Bianchi", "Mirko"),
            new Anagrafica(193460, "Verdi", "Marco"),
            new Anagrafica(234746, "Verdi", "Marco"),
        };
        private static List<Utente> utenti = new List<Utente> {
            new Utente(190290, "mirko.bianchi", "kjn4_2", "mirko.bianchi@itis.pr.it"),
            new Utente(193460, "marco.verdi", "poi9_A", "marco.verdi@itis.pr.it"),
            new Utente(234746, "marco.verdi1", "asAwL_c", "marco.verdi1@itis.pr.it"),
        };

        public static Anagrafica FindAnagrafica(Utente utente)
        {
            foreach (var anagrafica in anagrafiche)
            {
                if (anagrafica.Id == utente.Id)
                {
                    return anagrafica;
                }
            }

            throw new Exception("Utente non trovato");
        }

        private static Utente FindUtente(Anagrafica anagrafica)
        {
            foreach (var utente in utenti)
            {
                if (utente.Id == anagrafica.Id)
                {
                    return utente;
                }
            }

            throw new Exception("Utente non trovato");
        }

        private static bool UserExists(string user)
        {
            foreach (var utente in utenti)
            {
                if (utente.User == user)
                {
                    return true;
                }
            }

            return false;
        }

        public static void Register(string nome, string cognome)
        {
            var random = new Random();

            var id = random.Next();
            var user = nome + "." + cognome;
            var counter = 1;
            while (UserExists(user))
            {
                user = nome + "." + cognome + counter++;
            }
            var password = random.Next().ToString();
            var email = user + "@itis.pr.it";

            var anagrafica = new Anagrafica(id, cognome, nome);
            var utente = new Utente(id, user, password, email);

            anagrafiche.Add(anagrafica);
            utenti.Add(utente);
        }

        public static Utente Login(string username, string password)
        {
            foreach (var utente in utenti)
            {
                if (utente.User == username)
                {
                    if (utente.Password != password)
                    {
                        throw new Exception("Password sbagliata");
                    }

                    return utente;
                }
            }

            throw new Exception("Utente non trovato");
        }

        public static DataTable DTUtenti
        {
            get {
                var dt = new DataTable();

                dt.TableName = "Utenti";

                dt.Columns.Add("ID");
                dt.Columns.Add("Cognome");
                dt.Columns.Add("Nome");
                dt.Columns.Add("User");
                dt.Columns.Add("Password");
                dt.Columns.Add("Email");

                foreach (var anagrafica in anagrafiche)
                {
                    var utente = FindUtente(anagrafica);

                    var dr = dt.NewRow();

                    dr[0] = anagrafica.Id;
                    dr[1] = anagrafica.Cognome;
                    dr[2] = anagrafica.Nome;
                    dr[3] = utente.User;
                    dr[4] = utente.Password;
                    dr[5] = utente.Email;

                    dt.Rows.Add(dr);
                }

                return dt;
            }
        }
    }
}
