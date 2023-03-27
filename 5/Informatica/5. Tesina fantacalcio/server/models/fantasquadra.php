<?php
require_once "base.php";
require_once __DIR__."/../database/database.php";
require_once "fantalega.php";
require_once "giocatore.php";
require_once "utente.php";

/**
 * Classe che rappresenta una fantasquadra.
 */
class Fantasquadra extends Base {
	public $fantasquadra_id;
	public $nome;
	public $fantalega_id;
	public $utente_id;
	public $crediti;

	function __construct(
		$fantasquadra_id,
		$nome,
		$fantalega_id,
		$utente_id,
		$crediti
	) {
		$this->fantasquadra_id = $fantasquadra_id;
		$this->nome = $nome;
		$this->fantalega_id = $fantalega_id;
		$this->utente_id = $utente_id;
		$this->crediti = $crediti;
	}

	/**
	 * Ottieni informazioni sull'utente della fantasquadra.
	 * @return null|Utente utente della fantasquadra, null se non trovato
	 */
	function get_utente() {
		return Utente::from_id($this->utente_id);
	}

	/**
	 * Ottieni informazioni sulla fantalega della fantasquadra.
	 * @return null|Fantalega fantalega della fantasquadra, null se non trovato
	 */
	function get_fantalega() {
		return Fantalega::from_id($this->fantalega_id);
	}

	/**
	 * Ottieni la lista di giocatori della fantasquadra.
	 * @return null|Giocatore[] lista di giocatori della fantasquadra, null se non trovata
	 */
	function get_giocatori() {
		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$giocatori = array();

		$sql = "SELECT *".
			   " FROM giocatori_in_fantasquadre INNER JOIN giocatori ON giocatori_in_fantasquadre.giocatore_id = giocatori.giocatore_id".
			   " WHERE giocatori_in_fantasquadre.fantasquadra_id = ?";
		$statement = $conn->prepare($sql);
		$statement->bind_param("i", $this->fantasquadra_id);
		$statement->execute();

		$result = $statement->get_result();
		if ($result) {
			while ($row = $result->fetch_assoc()) {
				$giocatori[] = Giocatore::from_assoc_array($row);
			}
		}

		$conn->close();

		return $giocatori;
	}

	/**
	 * Imposta i crediti della fantasquadra.
	 * @param int $crediti crediti della fantasquadra
	 */
	function set_crediti($crediti) {
		$conn = Database::get_connection();
		if (!$conn) {
			return false;
		}

		$sql = "UPDATE fantasquadre".
			   " SET crediti = $crediti".
			   " WHERE fantasquadre.fantasquadra_id = ?";
		$statement = $conn->prepare($sql);
		$statement->bind_param("i", $this->fantasquadra_id);
		$statement->execute();

		$conn->close();

		return true;
	}

	/**
	 * Ottieni i crediti spesi per un giocatore.
	 * @param int $giocatore_id id del giocatore
	 * @return null|int crediti spesi per il giocatore, null se non trovato
	 */
	function get_crediti_spesi_per_giocatore($giocatore_id) {
		$giocatore_id = intval($giocatore_id);

		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$sql = "SELECT crediti_spesi".
			   " FROM giocatori_in_fantasquadre".
			   " WHERE giocatori_in_fantasquadre.fantasquadra_id = ?".
			   " AND giocatori_in_fantasquadre.giocatore_id = ?";
		$statement = $conn->prepare($sql);
		$statement->bind_param("ii", $this->fantasquadra_id, $giocatore_id);
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

		return $row["crediti_spesi"];
	}

	/**
	 * Assegna un giocatore alla fantasquadra.
	 * @param int $giocatore_id id del giocatore
	 * @param int $crediti_spesi crediti spesi per il giocatore
	 * @return bool true se l'assegnazione è andata a buon fine, false altrimenti
	 */
	function assegna_giocatore($giocatore_id, $crediti_spesi) {
		$giocatore_id = intval($giocatore_id);
		$crediti_spesi = intval($crediti_spesi);

		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		// Controlla che il giocatore non sia già stato assegnato in una fantasquadra della stessa fantalega
		$sql = "SELECT *".
			   " FROM giocatori_in_fantasquadre INNER JOIN fantasquadre ON fantasquadre.fantasquadra_id = giocatori_in_fantasquadre.fantasquadra_id".
			   "                                INNER JOIN fantaleghe ON fantaleghe.fantalega_id = fantasquadre.fantalega_id".
				" WHERE giocatori_in_fantasquadre.giocatore_id = ? AND fantaleghe.fantalega_id = (SELECT fantalega_id FROM fantasquadre WHERE fantasquadra_id = ?)";
		$statement = $conn->prepare($sql);
		$statement->bind_param("ii", $giocatore_id, $this->fantasquadra_id);
		$statement->execute();

		$result = $statement->get_result();
		if (!$result) {
			return null;
		}

		if ($result->num_rows > 0) {
			return null;
		}

		$sql = "INSERT INTO giocatori_in_fantasquadre (fantasquadra_id, giocatore_id, crediti_spesi) VALUES (?, ?, ?)";
		$statement = $conn->prepare($sql);
		$statement->bind_param("iii", $this->fantasquadra_id, $giocatore_id, $crediti_spesi);
		$statement->execute();

		$sql = "UPDATE fantasquadre SET crediti = crediti - ? WHERE fantasquadra_id = ?";
		$statement = $conn->prepare($sql);
		$statement->bind_param("ii", $crediti_spesi, $this->fantasquadra_id);
		$statement->execute();

		$conn->close();

		return true;
	}

	/**
	 * Svincola un giocatore dalla fantasquadra.
	 * @param int $giocatore_id id del giocatore
	 * @return bool true se lo svincolo è andato a buon fine, false altrimenti
	 */
	function svincola_giocatore($giocatore_id) {
		$giocatore_id = intval($giocatore_id);

		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$sql = "UPDATE fantasquadre".
			   " SET crediti = crediti + (SELECT crediti_spesi FROM giocatori_in_fantasquadre WHERE fantasquadra_id = ? AND giocatore_id = ?)".
			   " WHERE fantasquadra_id = ?";
		$statement = $conn->prepare($sql);
		$statement->bind_param("iii", $this->fantasquadra_id, $giocatore_id, $this->fantasquadra_id);
		$statement->execute();

		$sql = "DELETE FROM giocatori_in_fantasquadre WHERE fantasquadra_id = ? AND giocatore_id = ?";
		$statement = $conn->prepare($sql);
		$statement->bind_param("ii", $this->fantasquadra_id, $giocatore_id);
		$statement->execute();

		$conn->close();

		return true;
	}

    /**
     * Restituisce una fantasquadra dato un array associativo.
     * @param array $array array associativo avente come chiavi le colonne della tabella fantasquadre
     * @return Fantasquadra fantasquadra
     */
    static function from_assoc_array($array) {
        return new Fantasquadra(
            $array["fantasquadra_id"],
            $array["nome"],
            $array["fantalega_id"],
            $array["utente_id"],
            $array["crediti"]
        );
    }

	/**
	 * Restituisce una fantasquadra dato il suo ID.
	 * @param int $fantasquadra_id ID della fantasquadra
	 * @return null|Fantasquadra fantasquadra, null se non trovata
	 */
	static function from_id($fantasquadra_id) {
		$fantasquadra_id = intval($fantasquadra_id);

		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$sql = "SELECT *".
			   " FROM fantasquadre".
			   " WHERE fantasquadre.fantasquadra_id = ?";
		$statement = $conn->prepare($sql);
		$statement->bind_param("i", $fantasquadra_id);
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

		return Fantasquadra::from_assoc_array($row);
	}

	/**
	 * Inserisce dentro al database una nuova fantasquadra creata da un utente.
	 * @param string $nome nome della fantasquadra
	 * @param int $fantalega_id ID della fantalega
	 * @param int $utente_id ID dell'utente
	 * @return null|Fantasquadra fantasquadra creata, null se non creata
	 */
	static function crea_fantasquadra(
		$nome,
		$fantalega_id,
		$utente_id,
		$crediti
	) {
		$fantalega_id = intval($fantalega_id);
		$utente_id = intval($utente_id);
		$crediti = intval($crediti);

		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$sql = "INSERT INTO fantasquadre (nome, fantalega_id, utente_id, crediti) VALUES (?, ?, ?, ?)";
		$statement = $conn->prepare($sql);
		$statement->bind_param("siii", $nome, $fantalega_id, $utente_id, $crediti);
		$statement->execute();

		$fantasquadra_id = $conn->insert_id;

		$conn->close();

		$fantasquadra = Fantasquadra::from_id($fantasquadra_id);

		return $fantasquadra;
	}
}
?>
