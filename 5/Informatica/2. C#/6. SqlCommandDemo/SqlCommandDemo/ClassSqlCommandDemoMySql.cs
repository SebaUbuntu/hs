using System;
using System.Collections.Generic;
using System.Text;
using System.Data;
// NECESSARIO AGGIUNGERE IL RIFERIMENTO (dopo aver installato ADO.NET Driver for MySQL (Connector/NET) dal sito http://www.mysql.it/products/connector) a MySql.Data
using MySql.Data.MySqlClient;
namespace SqlCommandDemo
{
    class ClassSqlCommandDemoMySql
    {
        // http://www.connectionstrings.com
        private string stringaConnMySql = "Server=localhost;Uid=root;Pwd=''";
        private string stringaConn = "Server=localhost;Database=Prova;Uid=root;Pwd=''";
        private string stringaConnUtenteProva = "Server=localhost;Database=Prova;Uid=prova;Pwd=12345678";

        public void CreaProva()
        {
            MySqlConnection conn = new MySqlConnection(stringaConnMySql);
            MySqlCommand com = new MySqlCommand();
            com.Connection = conn;
            conn.Open();
            com.CommandText = "CREATE DATABASE Prova";
            try
            {
                com.ExecuteNonQuery();
            }
            catch (Exception err)
            {
                Console.WriteLine(err.Message);
            }

            conn.Close();
        }

        public void CreaTabelle()
        {
            MySqlConnection conn = new MySqlConnection(stringaConn);
            MySqlCommand com = new MySqlCommand();
            com.Connection = conn;
            conn.Open();
            com.CommandText =
                "CREATE TABLE Clienti(cod int(11) NOT NULL,	nome varchar(50) NOT NULL, piva varchar(11) NOT NULL, PRIMARY KEY (cod));"+
                "CREATE TABLE Fatture(cod int(11) NOT NULL,	importoFattura decimal(10,2) NULL, FOREIGN KEY (cod) REFERENCES Clienti(cod));";
            try
            {
                com.ExecuteNonQuery();
            }
            catch (Exception err)
            {
                Console.WriteLine(err.Message);
            }

            com.CommandText =
                "CREATE VIEW ClientiConFatture AS " +
                "SELECT Clienti.cod, Clienti.nome, Clienti.piva, Fatture.importoFattura " +
                "FROM Clienti INNER JOIN Fatture ON Clienti.cod = Fatture.cod " +
                "ORDER BY Clienti.nome;";
            try
            {
                com.ExecuteNonQuery();
            }
            catch (Exception err)
            {
                Console.WriteLine(err.Message);
            }

            com.CommandText =
                "CREATE PROCEDURE CancellaRecordTabelle " +
                "BEGIN " +
                "DELETE FROM Fatture; " +
                "DELETE FROM Clienti; " +
                "END;";
            try
            {
                com.ExecuteNonQuery();
            }
            catch (Exception err)
            {
                Console.WriteLine(err.Message);
            }

            com.CommandText =
                "CREATE TRIGGER `provaTrigger` BEFORE DELETE ON `clienti` FOR EACH ROW DELETE FROM fatture WHERE cod = OLD.cod";
            try
            {
                com.ExecuteNonQuery();
            }
            catch (Exception err)
            {
                Console.WriteLine(err.Message);
            }

            conn.Close();
        }
        
        public void PopolaClienti()
        {
            MySqlConnection conn = new MySqlConnection(stringaConn);
            MySqlCommand com = new MySqlCommand();
            com.Connection = conn;
            conn.Open();
            com.CommandText =
                "INSERT INTO Clienti (cod,nome,piva) VALUES(1,'aaa','11111111111');" +
                "INSERT INTO Clienti (cod,nome,piva) VALUES(2,'bbb','22222222222');" +
                "INSERT INTO Clienti (cod,nome,piva) VALUES(3,'ccc','33333333333');" +
                "INSERT INTO Clienti (cod,nome,piva) VALUES(4,'ddd','44444444444');" +
                "INSERT INTO Clienti (cod,nome,piva) VALUES(5,'eee','55555555555');" +
                "INSERT INTO Fatture (cod,importoFattura) VALUES(1,10.50);" +
                "INSERT INTO Fatture (cod,importoFattura) VALUES(1,20.35);" +
                "INSERT INTO Fatture (cod,importoFattura) VALUES(2,34.10);" +
                "INSERT INTO Fatture (cod,importoFattura) VALUES(3,56.00);" +
                "INSERT INTO Fatture (cod,importoFattura) VALUES(3,45.80);" +
                "INSERT INTO Fatture (cod,importoFattura) VALUES(5,5.00);";

            try
            {
                com.ExecuteNonQuery();
            }
            catch (Exception err)
            {
                Console.WriteLine(err.Message);
            }
            conn.Close();
        }

        public DataTable GetTabellaClienti()
        {
            MySqlConnection conn = new MySqlConnection(stringaConn);
            MySqlCommand com = new MySqlCommand();
            MySqlDataAdapter adapter = new MySqlDataAdapter();
            DataTable tabella = new DataTable();

            com.Connection = conn;
            com.CommandText = "SELECT * FROM Clienti";
            adapter.SelectCommand = com;
            adapter.Fill(tabella);
            return tabella;
        }

        public DataTable GetTabellaClientiFatture(string nome)
        {
            MySqlConnection conn = new MySqlConnection(stringaConn);
            MySqlCommand com = new MySqlCommand("SELECT * FROM ClientiConFatture WHERE nome = @nome", conn);
            MySqlDataAdapter adapter = new MySqlDataAdapter();
            DataTable tabella = new DataTable();

            com.Parameters.Add("@nome", MySqlDbType.VarChar);
            com.Parameters["@nome"].Value = nome;
            adapter.SelectCommand = com;
            adapter.Fill(tabella);
            return tabella;
        }

        public void CancellaRecord() {
            MySqlConnection con = new MySqlConnection(stringaConn);
            MySqlCommand com = new MySqlCommand();
            com.Connection = con;
            con.Open();
            com.CommandText = "CancellaRecordTabelle ";
            com.CommandType = CommandType.StoredProcedure;
            com.ExecuteNonQuery();
            con.Close();
        }

        public void CreaUtenteProva() {
            MySqlConnection con = new MySqlConnection(stringaConn);
            MySqlCommand com = new MySqlCommand();
            com.Connection = con;
            con.Open();

            com.CommandText = "CREATE USER 'prova'@'localhost' IDENTIFIED BY '12345678';";
            try
            {
                com.ExecuteNonQuery();
            }
            catch (Exception err)
            {
                Console.WriteLine(err.Message);
            }

            con.Close();
        }

        public DataTable GetTabellaClientiConUtenteProva()
        {
            MySqlConnection conn = new MySqlConnection(stringaConnUtenteProva);
            MySqlCommand com = new MySqlCommand();
            MySqlDataAdapter adapter = new MySqlDataAdapter();
            DataTable tabella = new DataTable();

            com.Connection = conn;
            com.CommandText = "SELECT * FROM Clienti";
            adapter.SelectCommand = com;
            adapter.Fill(tabella);
            return tabella;
        }

        public void UpdateTabellaClienti(DataTable dtClienti)
        {
            MySqlConnection conn = new MySqlConnection(stringaConnUtenteProva);
            MySqlCommand com = new MySqlCommand();
            MySqlDataAdapter adapter = new MySqlDataAdapter();

            com.Connection = conn;

            com.CommandText = "UPDATE Clienti SET nome = @nome, piva = @piva WHERE cod = @cod";
            com.Parameters.Add("@cod", MySqlDbType.Int32, 11, "cod");
            com.Parameters.Add("@nome", MySqlDbType.VarChar, 50, "nome");
            com.Parameters.Add("@piva", MySqlDbType.VarChar, 11, "piva");
            //parameter.SourceVersion = DataRowVersion.Original;

            /*
            adapter.Update scorrerà tutte le righe modificate della DataTable e applicherà per ognuna il CommandText con i parametri sostituiti correttamente
            */

            adapter.UpdateCommand = com;
            adapter.Update(dtClienti);
        }

        public void UpdateTabellaClientiAutomatica(DataTable dtClienti)
        {
            MySqlConnection conn = new MySqlConnection(stringaConnUtenteProva);
            MySqlCommand com = new MySqlCommand("SELECT * FROM CLIENTI");
            MySqlDataAdapter adapter = new MySqlDataAdapter(com);
            com.Connection = conn;
            MySqlCommandBuilder cb = new MySqlCommandBuilder(adapter);
            adapter.Update(dtClienti);
        }
        
         public void AssegnaDirittiAUtenteProva(bool assegna) {
            MySqlConnection con = new MySqlConnection(stringaConn);
            MySqlCommand com = new MySqlCommand();
            com.Connection = con;
            con.Open();

            if (assegna)
                com.CommandText = "GRANT SELECT ON prova.* TO prova@localhost WITH GRANT OPTION;";
            else
                com.CommandText = "REVOKE ALL PRIVILEGES ON prova.* FROM prova@localhost; REVOKE GRANT OPTION ON prova.* FROM prova@localhost;";
            try
            {
                com.ExecuteNonQuery();
            }
            catch (Exception err)
            {
                Console.WriteLine(err.Message);
            }

            con.Close();
        }
    }
}