using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.IO;
using System.Linq;
// NECESSARIO AGGIUNGERE IL RIFERIMENTO alla libreria DLL del connettore (Connector/NET) MySql.Data inclusa tra i file di questo progetto (scaricata dal sito http://www.mysql.it/products/connector)
using MySql.Data.MySqlClient;
namespace WindowsFormsDBArte
{
    class ClassMySqlDati
    {
        private string stringaConn = "Server=localhost;Database=arte;Uid=root;Pwd=''";
        private string stringaConnSenzaDB = "Server=localhost;Uid=root;Pwd=''";
        private MySqlDataAdapter adapter = new MySqlDataAdapter();

        public ClassMySqlDati() 
        {
            ConfiguraUpdateTabellaQuadri();
        }
        public Boolean isStartedMysql()
        {
            MySqlConnection conn = new MySqlConnection(stringaConnSenzaDB);
            try
            {
                conn.Open();
            }
            catch
            {
                return false;
            }
            return true;
        }
        public void CreaDB_Dati()
        {
            string script = File.ReadAllText(@"Arte_Creazione_Database_Tabelle.sql");
            MySqlConnection conn = new MySqlConnection(stringaConnSenzaDB);
            MySqlCommand com = new MySqlCommand(script, conn);
            conn.Open();
            com.ExecuteNonQuery();
            script = File.ReadAllText(@"Arte_Popola_Database.sql");
            com.CommandText = script;
            com.ExecuteNonQuery();
        }
        /// <summary>
        /// Restituisce DataTable da DB con lista artisti
        /// </summary>
        /// <returns></returns>
        public DataTable GetTabellaArtisti()
        {
            MySqlConnection conn = new MySqlConnection(stringaConn);
            MySqlCommand com = new MySqlCommand();
            MySqlDataAdapter adapter = new MySqlDataAdapter();
            DataTable tabella = new DataTable();

            com.Connection = conn;
            com.CommandText = "SELECT * FROM Artisti";
            adapter.SelectCommand = com;
            adapter.Fill(tabella);
            return tabella;
        }
        /// <summary>
        /// Restituisce DataTable da DB con lista mysei
        /// </summary>
        /// <returns></returns>
        public DataTable GetTabellaMusei()
        {
            MySqlConnection conn = new MySqlConnection(stringaConn);
            MySqlCommand com = new MySqlCommand();
            MySqlDataAdapter adapter = new MySqlDataAdapter();
            DataTable tabella = new DataTable();

            com.Connection = conn;
            com.CommandText = "SELECT * FROM Musei";
            adapter.SelectCommand = com;
            adapter.Fill(tabella);
            return tabella;
        }
        /// <summary>
        /// Restituisce String da DB con codice museo dato nome
        /// </summary>
        /// <param name="nome"></param>
        /// <returns></returns>
        public String GetCodiceMuseoConNome(String nome)
        {
            MySqlConnection conn = new MySqlConnection(stringaConn);
            MySqlCommand com = new MySqlCommand();
            com.Connection = conn;
            com.CommandText = $"SELECT MM_CodiceMuseo FROM musei where MM_Nome = '{nome}'";
            conn.Open();
            return com.ExecuteScalar().ToString();
        }
        /// <summary>
        /// Restituisce String da DB con codice artista dato nome
        /// </summary>
        /// <param name="nome"></param>
        /// <returns></returns>
        public String GetCodiceArtistaConNome(String nome)
        {
            MySqlConnection conn = new MySqlConnection(stringaConn);
            MySqlCommand com = new MySqlCommand();
            com.Connection = conn;
            com.CommandText = $"SELECT AR_CodiceArtista FROM artisti where AR_Nome = '{nome}'";
            conn.Open();
            return com.ExecuteScalar().ToString();
        }
        /// <summary>
        /// Restituisce DataTable da DB con quadri di artista fornendo codice museo
        /// </summary>
        /// <param name="codMuseo"></param>
        /// <returns></returns>
        public DataTable GetListaQuadriDaCodiceMuseo(String codMuseo)
        {
            MySqlConnection conn = new MySqlConnection(stringaConn);
            MySqlCommand com = new MySqlCommand();
            MySqlDataAdapter adapter = new MySqlDataAdapter();
            DataTable tabella = new DataTable();

            com.Connection = conn;
            com.CommandText = $"SELECT * FROM quadri where QQ_CodiceMuseo = '{codMuseo}'";
            adapter.SelectCommand = com;
            adapter.Fill(tabella);
           
            return tabella;
        }
        /// <summary>
        /// Restituisce DataTable con dati da DB con quadri fornendo codice artista
        /// </summary>
        /// <param name="codArtista"></param>
        /// <returns></returns>
        public DataTable GetListaQuadriDaCodiceArtista(String codArtista)
        {
            MySqlConnection conn = new MySqlConnection(stringaConn);
            MySqlCommand com = new MySqlCommand();
            MySqlDataAdapter adapter = new MySqlDataAdapter();
            DataTable tabella = new DataTable();

            com.Connection = conn;
            com.CommandText = $"SELECT * FROM quadri where QQ_CodiceArtista = '{codArtista}'";
            adapter.SelectCommand = com;
            adapter.Fill(tabella);

            return tabella;
        }
        /// <summary>
        ///  Restituisce dati da DB in List di musei
        /// </summary>
        /// <returns></returns>
        public List<String> GetListaMusei()
        {
            MySqlConnection conn = new MySqlConnection(stringaConn);
            MySqlCommand com = new MySqlCommand();
            MySqlDataAdapter adapter = new MySqlDataAdapter();
            DataTable tabella = new DataTable();

            com.Connection = conn;
            com.CommandText = "SELECT * FROM musei";
            adapter.SelectCommand = com;
            adapter.Fill(tabella);
            // In LINQ
            return tabella.AsEnumerable().Select(r => string.Format("{0}", r["MM_Nome"])).ToList();
            /* EQUIVALENTE A
            List<String> artisti = new List<string>();
            foreach (DataRow a in tabella.Rows)
                artisti.Add(a["MM_Nome"].ToString());
            return artisti;*/
        }
        /// <summary>
        /// Restituisce dati da DB in DataTable con lista artisti
        /// </summary>
        /// <returns></returns>
        public List<String> GetListaArtisti()
        {
            MySqlConnection conn = new MySqlConnection(stringaConn);
            MySqlCommand com = new MySqlCommand();
            MySqlDataAdapter adapter = new MySqlDataAdapter();
            DataTable tabella = new DataTable();

            com.Connection = conn;
            com.CommandText = "SELECT * FROM artisti";
            adapter.SelectCommand = com;
            adapter.Fill(tabella);
            return tabella.AsEnumerable().Select(r => string.Format("{0}", r["AR_Nome"])).ToList();
        }

        /// <summary>
        /// restituisce DataTable con dati da db join tra 3 tabelle
        /// </summary>
        /// <param name="nome"></param>
        /// <returns></returns>
        public DataTable GetTabellaQuadriJoinAutoriMusei(string nome)
        {
            MySqlConnection conn = new MySqlConnection(stringaConn);
            MySqlCommand com = new MySqlCommand("SELECT quadri.QQ_TitoloQuadro, quadri.QQ_AnnoEsecuzione, artisti.AR_Nome, musei.MM_Nome FROM quadri INNER JOIN artisti ON artisti.AR_CodiceArtista=quadri.QQ_CodiceArtista INNER JOIN musei ON quadri.QQ_CodiceMuseo=musei.MM_CodiceMuseo WHERE quadri.QQ_TitoloQuadro LIKE @nome", conn);
            MySqlDataAdapter adapter = new MySqlDataAdapter();
            DataTable tabella = new DataTable();

            com.Parameters.Add("@nome", MySqlDbType.VarChar);
            com.Parameters["@nome"].Value = nome;
            adapter.SelectCommand = com;
            adapter.Fill(tabella);
            return tabella;
        }

        /// <summary>
        /// Per invocare una eventuale Stored Procedure nel DB
        /// </summary>
        public void CancellaRecordTabelle() {
            MySqlConnection con = new MySqlConnection(stringaConn);
            MySqlCommand com = new MySqlCommand();
            com.Connection = con;
            con.Open();
            com.CommandText = "CancellaRecordTabelle";
            com.CommandType = CommandType.StoredProcedure;
            com.ExecuteNonQuery();
            con.Close();
        }
        /// <summary>
        /// E' il metodo più importante: configura il DataTableAdapter (attributo di questa classe) per eseguire in automatico
        /// le 3 modifiche classiche (insert, update, delete)
        /// </summary>
        private void ConfiguraUpdateTabellaQuadri()
        {
            MySqlConnection conn = new MySqlConnection(stringaConn);

            MySqlCommand comSelect = new MySqlCommand();
            comSelect.Connection = conn;
            comSelect.CommandText = "SELECT * FROM quadri";
            adapter.SelectCommand = comSelect;

            MySqlCommand comUpdate = new MySqlCommand();
            comUpdate.Connection = conn;
            comUpdate.CommandText = "UPDATE quadri SET QQ_TitoloQuadro=@titolo,QQ_CodiceArtista=@codArtista,QQ_AnnoEsecuzione=@anno,QQ_Tecnica=@tecnica,QQ_Altezza=@altezza,QQ_Larghezza=@larghezza,QQ_CodiceMuseo=@codMuseo,QQ_Note=@note WHERE QQ_TitoloQuadro=@oldTitolo";
            comUpdate.Parameters.Add("@titolo", MySqlDbType.VarChar, 100, "QQ_TitoloQuadro");
            comUpdate.Parameters.Add("@codArtista", MySqlDbType.VarChar, 4, "QQ_CodiceArtista");
            comUpdate.Parameters.Add("@anno", MySqlDbType.Int32, 11, "QQ_AnnoEsecuzione");
            comUpdate.Parameters.Add("@tecnica", MySqlDbType.VarChar, 100, "QQ_Tecnica");
            comUpdate.Parameters.Add("@altezza", MySqlDbType.Int32, 11, "QQ_Altezza");
            comUpdate.Parameters.Add("@larghezza", MySqlDbType.Int32, 11, "QQ_Larghezza");
            comUpdate.Parameters.Add("@codMuseo", MySqlDbType.VarChar, 3, "QQ_CodiceMuseo");
            comUpdate.Parameters.Add("@note", MySqlDbType.VarChar, 100, "QQ_Note");
            MySqlParameter p = comUpdate.Parameters.Add("@oldTitolo", MySqlDbType.VarChar, 40, "QQ_TitoloQuadro");
            p.SourceVersion = DataRowVersion.Original;
            adapter.UpdateCommand = comUpdate;

            MySqlCommand comInsert = new MySqlCommand();
            comInsert.Connection = conn;
            comInsert.CommandText = "INSERT INTO quadri(QQ_TitoloQuadro, QQ_CodiceArtista, QQ_AnnoEsecuzione, QQ_Tecnica, QQ_Altezza, QQ_Larghezza, QQ_CodiceMuseo, QQ_Note) VALUES (@titolo,@codArtista,@anno,@tecnica,@altezza,@larghezza,@codMuseo,@note)";
            comInsert.Parameters.Add("@titolo", MySqlDbType.VarChar, 100, "QQ_TitoloQuadro");
            comInsert.Parameters.Add("@codArtista", MySqlDbType.VarChar, 4, "QQ_CodiceArtista");
            comInsert.Parameters.Add("@anno", MySqlDbType.Int32, 11, "QQ_AnnoEsecuzione");
            comInsert.Parameters.Add("@tecnica", MySqlDbType.VarChar, 100, "QQ_Tecnica");
            comInsert.Parameters.Add("@altezza", MySqlDbType.Int32, 11, "QQ_Altezza");
            comInsert.Parameters.Add("@larghezza", MySqlDbType.Int32, 11, "QQ_Larghezza");
            comInsert.Parameters.Add("@codMuseo", MySqlDbType.VarChar, 3, "QQ_CodiceMuseo");
            comInsert.Parameters.Add("@note", MySqlDbType.VarChar, 100, "QQ_Note");
            adapter.InsertCommand = comInsert;

            MySqlCommand comDelete = new MySqlCommand();
            comDelete.Connection = conn;
            comDelete.CommandText = "DELETE FROM quadri WHERE QQ_TitoloQuadro=@titolo";
            comDelete.Parameters.Add("@titolo", MySqlDbType.VarChar, 40, "QQ_TitoloQuadro");
            adapter.DeleteCommand = comDelete;
        }

        /// <summary>
        /// Esegue Update su DataTable passata in base all'adapter configurato in precedenzao
        /// </summary>
        /// <param name="dtQuadri"></param>
        public void UpdateTabellaQuadri(DataTable dtQuadri)
        {
            // adapter.Update scorre tutte le righe modificate della DataTable e applicherà per ognuna il CommandText opportuno (update, insert, delete) con i parametri valorizzati correttamente
            adapter.Update(dtQuadri);
        }

        /// <summary>
        /// Funziona esclusivamente se anche la DataTable è configurata con primary key
        /// </summary>
        /// <param name="dtQuadri"></param>
        public void UpdateTabellaQuadriAutomatica(DataTable dtQuadri)
        {
            MySqlConnection conn = new MySqlConnection(stringaConn);
            MySqlCommand com = new MySqlCommand("SELECT * FROM quadri");
            MySqlDataAdapter adapter = new MySqlDataAdapter(com);
            com.Connection = conn;
            MySqlCommandBuilder cb = new MySqlCommandBuilder(adapter);
            adapter.Update(dtQuadri);
        }
    }
}