using System;
using System.Collections.Generic;
using System.Text;
using System.Data;
// NECESSARIO AGGIUNGERE IL RIFERIMENTO (dopo aver installato ADO.NET Driver for MySQL (Connector/NET) dal sito http://www.mysql.it/products/connector) a MySql.Data
using MySql.Data.MySqlClient;
using System.Data.SqlClient;

namespace WindowsFormsAppMySQL2
{
    class Database
    {
        // http://www.connectionstrings.com
        private string stringaConn = "Server=localhost;Database=DBCinema;Uid=root;Pwd=''";

        public DataTable GetFilm(int release_year/*, int genere_id*/)
        {
            MySqlConnection conn = new MySqlConnection(stringaConn);
            MySqlCommand com = new MySqlCommand("SELECT * FROM film WHERE release_year = @release_year", conn);
            MySqlDataAdapter adapter = new MySqlDataAdapter();
            DataTable tabella = new DataTable();

            com.Parameters.Add("@release_year", MySqlDbType.Year);
            com.Parameters["@release_year"].Value = release_year;
            adapter.SelectCommand = com;
            adapter.Fill(tabella);
            return tabella;
        }

        public void UpdateFilm(DataTable film)
        {
            MySqlConnection conn = new MySqlConnection(stringaConn);
            MySqlCommand com = new MySqlCommand("SELECT * FROM film");
            MySqlDataAdapter adapter = new MySqlDataAdapter(com);
            com.Connection = conn;
            MySqlCommandBuilder cb = new MySqlCommandBuilder(adapter);
            adapter.Update(film);
        }
    }
}
