<?php
require_once "base.php";
require_once __DIR__."/../database/database.php";
require_once "fantalega.php";

/**
 * Classe che rappresenta un utente del sito.
 */
class Utente extends Base {
	public $utente_id;
	public $username;
	public $nome;
	public $cognome;
	public $email;

	function __construct($utente_id, $username, $nome, $cognome, $email) {
		$this->utente_id = $utente_id;
		$this->username = $username;
		$this->nome = $nome;
		$this->cognome = $cognome;
		$this->email = $email;
	}

	/**
	 * Ottieni la lista delle fantaleghe di cui l'utente fa parte.
	 */
	function get_fantaleghe_utente() {
		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$sql = "SELECT fantaleghe.*".
			   " FROM fantaleghe LEFT JOIN fantasquadre ON fantasquadre.fantalega_id = fantaleghe.fantalega_id".
			   " WHERE fantasquadre.utente_id = ? OR fantaleghe.admin_id = ?".
			   " GROUP BY fantaleghe.fantalega_id;";
		$statement = $conn->prepare($sql);
		$statement->bind_param("ii", $this->utente_id, $this->utente_id);
		$statement->execute();

		$result = $statement->get_result();
		if (!$result) {
			return null;
		}

		$fantaleghe = array();
		while ($row = $result->fetch_assoc()) {
			$fantaleghe[] = Fantalega::from_assoc_array($row);
		}

		$conn->close();

		return $fantaleghe;
	}

	/**
	 * Ottieni gli inviti attivi per l'utente.
	 */
	function get_inviti_attivi() {
		return Invito::get_inviti_utente($this->utente_id);
	}

	static function from_assoc_array($array) {
		return new Utente(
			$array['utente_id'],
			$array['username'],
			$array['nome'],
			$array['cognome'],
			$array['email'],
		);
	}

	/**
	 * Restituisce un utente dato il suo id.
	 * @param $utente_id id del giocatore da cercare
	 * @return null|Utente utente trovato, null se non trovato
	 */
	static function from_id($utente_id) {
		$utente_id = intval($utente_id);

		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$sql = "SELECT utenti.*".
			   " FROM utenti".
			   " WHERE utenti.utente_id = ?";
		$statement = $conn->prepare($sql);
		$statement->bind_param("i", $utente_id);
		$statement->execute();

		$result = $statement->get_result();
		if (!$result) {
			return null;
		}

		if ($result->num_rows == 0) {
			return null;
		}

		$row = $result->fetch_assoc();

		$conn->close();

		return Utente::from_assoc_array($row);
	}

	/**
	 * Restituisce un utente dato il suo username.
	 * @param $username username dell'utente da cercare
	 * @return null|Utente utente trovato, null se non trovato
	 */
	static function from_username($username) {
		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$sql = "SELECT utenti.*".
			   " FROM utenti".
			   " WHERE utenti.username = ?";
		$statement = $conn->prepare($sql);
		$statement->bind_param("s", $username);
		$statement->execute();

		$result = $statement->get_result();
		if (!$result) {
			return null;
		}

		if ($result->num_rows == 0) {
			return null;
		}

		$row = $result->fetch_assoc();

		$conn->close();

		return Utente::from_assoc_array($row);
	}

	/**
	 * Restituisce un utente dato la sua email.
	 * @param $email email dell'utente da cercare
	 * @return null|Utente utente trovato, null se non trovato
	 */
	static function from_email($email) {
		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$sql = "SELECT utenti.*".
			   " FROM utenti".
			   " WHERE utenti.email = ?";
		$statement = $conn->prepare($sql);
		$statement->bind_param("s", $email);
		$statement->execute();

		$result = $statement->get_result();
		if (!$result) {
			return null;
		}

		if ($result->num_rows == 0) {
			return null;
		}

		$row = $result->fetch_assoc();

		$conn->close();

		return Utente::from_assoc_array($row);
	}

	/**
	 * Restituisce un utente dato il suo username o la sua email.
	 * @param $username_or_email username o email dell'utente da cercare
	 * @return null|Utente utente trovato, null se non trovato
	 */
	static function from_username_or_email($username_or_email) {
		$utente = Utente::from_username($username_or_email);
		if ($utente) {
			return $utente;
		}

		$utente = Utente::from_email($username_or_email);
		if ($utente) {
			return $utente;
		}

		return null;
	}

	/**
	 * Funzione di login che cerca nel databese un utente con username e password corrispondenti.
	 * @param $username username dell'utente da cercare
	 * @param $password password dell'utente da cercare
	 * @return null|Utente utente trovato, null se non trovato
	 */
	static function login($username, $password) {
		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$sql = "SELECT utenti.*".
			   " FROM utenti".
			   " WHERE utenti.username = ? AND utenti.password = ?";
		$statement = $conn->prepare($sql);
		$statement->bind_param("ss", $username, $password);
		$statement->execute();

		$result = $statement->get_result();
		if (!$result) {
			return null;
		}

		if ($result->num_rows == 0) {
			return null;
		}

		$row = $result->fetch_assoc();

		$conn->close();

		return Utente::from_assoc_array($row);
	}

	/**
	 * Funzione di registrazione che inserisce un nuovo utente nel database.
	 * @param $username username dell'utente da registrare
	 * @param $password password dell'utente da registrare
	 * @param $nome nome dell'utente da registrare
	 * @param $cognome cognome dell'utente da registrare
	 * @param $email email dell'utente da registrare
	 * @return null|Utente utente registrato, null se non registrato
	 */
	static function register(
		$username,
		$password,
		$nome,
		$cognome,
		$email
	) {
		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		// Lock table
		$sql = "LOCK TABLES utenti WRITE";
		$query = $conn->query($sql);
		if (!$query) {
			return null;
		}

		// Controlla se l'utente esiste già
		$sql = "SELECT utenti.*".
			   " FROM utenti".
			   " WHERE utenti.username = ? OR utenti.email = ?";
		$statement = $conn->prepare($sql);
		$statement->bind_param("ss", $username, $email);
		$statement->execute();

		$result = $statement->get_result();
		if (!$result) {
			return null;
		}

		if ($result->num_rows > 0) {
			// Utente già esistente
			$conn->close();
			return null;
		}

		$sql = "INSERT INTO utenti (username, password, nome, cognome, email) VALUES (?, ?, ?, ?, ?)";
		$statement = $conn->prepare($sql);
		$statement->bind_param("sssss", $username, $password, $nome, $cognome, $email);
		$statement->execute();

		$result = $statement->get_result();

		$conn->close();

		return Utente::from_username($username);
	}
}
?>
