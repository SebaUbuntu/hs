using System;
using System.Collections.Generic;
using System.Text;
using System.Data;
using System.Data.SqlClient;

namespace SqlCommandDemo
{
    class ClassSqlCommandDemo
    {
        // http://www.connectionstrings.com
        private string stringaConnWin = "Server=.\\SQLExpress;Trusted_Connection=True;";
        private string stringaConnIstanzaServer = "Server=.\\SQLExpress;Database=Prova;Trusted_Connection=True;";
        private string stringaConnIstanzaUtente = "Data Source=.\\SQLExpress;Integrated Security=true;AttachDbFilename=" + System.Environment.CurrentDirectory + "\\Prova.mdf;User Instance=true";

        public void CreaProva()
        {
            SqlConnection conn = new SqlConnection(stringaConnWin);
            SqlCommand com = new SqlCommand();
            com.Connection = conn;
            conn.Open();
            com.CommandText = 
                "CREATE DATABASE [Prova] ON PRIMARY " +
                "  ( NAME = 'Prova', FILENAME = '" + System.Environment.CurrentDirectory + "\\Prova.mdf') " + 
                "  LOG ON " + 
                "  ( NAME = 'Prova_log', FILENAME = '" + System.Environment.CurrentDirectory + "\\Prova_log.ldf')";
            try
            {
                com.ExecuteNonQuery();
            }
            catch (Exception err)
            {
                Console.WriteLine(err.Message);
            }

            com.CommandText = "EXEC master.dbo.sp_detach_db @dbname = N'Prova', @keepfulltextindexfile=N'true'";

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
            SqlConnection conn = new SqlConnection(stringaConnIstanzaUtente);
            SqlCommand com = new SqlCommand();
            com.Connection = conn;
            conn.Open();
            com.CommandText =
                "CREATE TABLE [dbo].[Clienti](" +
                "[cod] [bigint] NOT NULL," +
                "[nome] [varchar](50) NOT NULL," +
                "[piva] [char](11) NOT NULL," +
                "CONSTRAINT pk_clienti " +
                "PRIMARY KEY(cod));" +
                "\n" +
                "CREATE TABLE [dbo].[Fatture](" +
                "[cod] [bigint] NOT NULL, " +
                "[importoFattura] [money] NULL, " +
                "CONSTRAINT fk_clienti " +
                "FOREIGN KEY (cod) " +
                "REFERENCES Clienti(cod));";
            try
            {
                com.ExecuteNonQuery();
            }
            catch (Exception err)
            {
                Console.WriteLine(err.Message);
            }

            com.CommandText =
                "CREATE VIEW [dbo].[ClientiConFatture] AS\n" +
                "SELECT TOP (100) PERCENT dbo.Clienti.cod, dbo.Clienti.nome, dbo.Clienti.piva, dbo.Fatture.importoFattura\n" +
                "FROM  dbo.Clienti INNER JOIN dbo.Fatture ON dbo.Clienti.cod = dbo.Fatture.cod\n" +
                "ORDER BY dbo.Clienti.nome;";
            try
            {
                com.ExecuteNonQuery();
            }
            catch (Exception err)
            {
                Console.WriteLine(err.Message);
            }

            com.CommandText =
                "CREATE FUNCTION fn_FatturePerCliente(@cliente VARCHAR(50))\n" +
                "RETURNS TABLE\n" +
                "AS\n" +
                "RETURN (\n" +
                "SELECT dbo.Clienti.cod, dbo.Clienti.nome, dbo.Clienti.piva, dbo.Fatture.importoFattura\n" + 
                "FROM   dbo.Clienti INNER JOIN dbo.Fatture ON dbo.Clienti.cod = dbo.Fatture.cod\n" +
                "WHERE dbo.Clienti.nome = @cliente );";
            try
            {
                com.ExecuteNonQuery();
            }
            catch (Exception err)
            {
                Console.WriteLine(err.Message);
            }

            com.CommandText =
                "CREATE FUNCTION [dbo].[fn_TotaleFatturePerCliente](@cliente VARCHAR(50))\n" +
                "RETURNS MONEY\n" +
                "AS\n" +
                "BEGIN\n" +
                "DECLARE @totale MONEY\n" +
                "SELECT @totale = SUM (dbo.Fatture.importoFattura)\n" +
                "FROM   dbo.Clienti INNER JOIN dbo.Fatture ON dbo.Clienti.cod = dbo.Fatture.cod\n" +
                "WHERE dbo.Clienti.nome = @cliente\n" +
                "RETURN @totale\n" +
                "END";
            try
            {
                com.ExecuteNonQuery();
            }
            catch (Exception err)
            {
                Console.WriteLine(err.Message);
            }

            com.CommandText =
                "CREATE PROCEDURE [dbo].[sp_CancellaRecordTabelle]\n" +
                "AS\n" +
                "BEGIN\n" +
                "DELETE FROM Fatture\n" +
                "DELETE FROM Clienti\n" +
                "END";
            try
            {
                com.ExecuteNonQuery();
            }
            catch (Exception err)
            {
                Console.WriteLine(err.Message);
            }
        }
        
        public void Popola()
        {
            SqlConnection conn = new SqlConnection(stringaConnIstanzaUtente);
            SqlCommand com = new SqlCommand();
            com.Connection = conn;
            conn.Open();
            com.CommandText =
                "INSERT INTO [Clienti] ([cod],[nome],[piva])VALUES(1,'aaa','11111111111');" +
                "INSERT INTO [Clienti] ([cod],[nome],[piva])VALUES(2,'bbb','22222222222');" +
                "INSERT INTO [Clienti] ([cod],[nome],[piva])VALUES(3,'ccc','33333333333');" +
                "INSERT INTO [Clienti] ([cod],[nome],[piva])VALUES(4,'ddd','44444444444');" +
                "INSERT INTO [Clienti] ([cod],[nome],[piva])VALUES(5,'eee','55555555555');" +
                "INSERT INTO [fatture] ([cod],[importoFattura])VALUES(1,10.00);" +
                "INSERT INTO [fatture] ([cod],[importoFattura])VALUES(1,20.00);" +
                "INSERT INTO [fatture] ([cod],[importoFattura])VALUES(2,34.00);" +
                "INSERT INTO [fatture] ([cod],[importoFattura])VALUES(3,56.00);" +
                "INSERT INTO [fatture] ([cod],[importoFattura])VALUES(3,45.00)";

            try
            {
                com.ExecuteNonQuery();
            }
            catch (Exception err)
            {
                Console.WriteLine(err.Message);
            }
        }

        public DataTable tabellaClienti()
        {
            SqlConnection conn = new SqlConnection(stringaConnIstanzaUtente);
            SqlCommand com = new SqlCommand();
            SqlDataAdapter adapter = new SqlDataAdapter();
            DataTable tabella = new DataTable();

            com.Connection = conn;
            com.CommandText = "select * from Clienti";
            adapter.SelectCommand = com;
            adapter.Fill(tabella);
            return tabella;
        }

        public DataTable tabellaClientiFatture(string nome)
        {
            SqlConnection conn = new SqlConnection(stringaConnIstanzaUtente);
            SqlCommand com = new SqlCommand("select * from ClientiConFatture where nome = @nome", conn);
            SqlDataAdapter adapter = new SqlDataAdapter();
            DataTable tabella = new DataTable();

            com.Parameters.Add("@nome", SqlDbType.VarChar);
            com.Parameters["@nome"].Value = nome;
            adapter.SelectCommand = com;
            adapter.Fill(tabella);
            return tabella;
        }

        public DataTable tabellaClientiFattureConFunction(string nome)
        {
            SqlConnection con = new SqlConnection(stringaConnIstanzaUtente);
            SqlCommand com = new SqlCommand();
            SqlDataAdapter adapter = new SqlDataAdapter(com);
            DataTable tabella = new DataTable();

            com.Connection = con;
            com.Parameters.Add("@nome", SqlDbType.VarChar);
            com.Parameters["@nome"].Value = nome;
            com.CommandText = "select * from fn_FatturePerCliente(@nome) order by importoFattura DESC";
            adapter.Fill(tabella);
            return tabella;
        }

        public double totaleFatturePerCliente(string nome)
        {
            SqlConnection con = new SqlConnection(stringaConnIstanzaUtente);
            SqlCommand com = new SqlCommand();
            com.Connection = con;
            con.Open();
            com.CommandText = "SELECT dbo.fn_TotaleFatturePerCliente('" + nome + "')";
            double tot = 0;
            try
            {
                tot = Convert.ToDouble(com.ExecuteScalar());
            }
            catch (Exception)
            {
                tot = 0;
            }
            return tot;
        }

        public void cancellaRecord() {
            SqlConnection con = new SqlConnection(stringaConnIstanzaUtente);
            SqlCommand com = new SqlCommand();
            com.Connection = con;
            con.Open();
            com.CommandType = CommandType.StoredProcedure;
            com.CommandText = "sp_CancellaRecordTabelle";
            com.ExecuteNonQuery();
        }
    }
}
