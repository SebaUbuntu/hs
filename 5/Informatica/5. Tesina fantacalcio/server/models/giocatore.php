<?php
require_once "base.php";
require_once __DIR__."/../database/database.php";

/**
 * Classe che rappresenta un giocatore di una squadra.
 */
class Giocatore extends Base {
	public $giocatore_id;
	public $cognome_nome;
	public $data_nascita;
	public $posizione;
	public $crediti_iniziali;
	public $crediti_finali;
	public $squadra_id;
	public $nazionalita;

	function __construct(
		$giocatore_id,
		$cognome_nome,
		$data_nascita,
		$posizione,
		$crediti_iniziali,
		$crediti_finali,
		$squadra_id,
		$nazionalita
	) {
		$this->giocatore_id = $giocatore_id;
		$this->cognome_nome = $cognome_nome;
		$this->data_nascita = $data_nascita;
		$this->posizione = $posizione;
		$this->crediti_iniziali = $crediti_iniziali;
		$this->crediti_finali = $crediti_finali;
		$this->squadra_id = $squadra_id;
		$this->nazionalita = $nazionalita;
	}

	/**
	 * Restituisce un giocatore dato un array associativo.
	 * @param $array array associativo con i dati del giocatore
	 * @return Giocatore giocatore creato
	 */
	static function from_assoc_array($array) {
		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		// Ottieni le nazionalità del giocatore
		$sql = "SELECT nazione FROM nazionalita_giocatori WHERE nazionalita_giocatori.giocatore_id = ?";
		$statement = $conn->prepare($sql);
		$statement->bind_param("i", $giocatore_id);
		$statement->execute();

		$result = $statement->get_result();
		if (!$result) {
			return null;
		}

		$nazionalita = array();
		while ($row = $result->fetch_assoc()) {
			$nazionalita[] = $row['nazione'];
		}

		$conn->close();

		return new Giocatore(
			$array["giocatore_id"],
			$array["cognome_nome"],
			$array["data_nascita"],
			$array["posizione"],
			$array["crediti_iniziali"],
			$array["crediti_finali"],
			$array["squadra_id"],
			$nazionalita
		);
	}

	/**
	 * Restituisce un giocatore dato il suo id.
	 * @param $giocatore_id id del giocatore da cercare
	 * @return null|Giocatore giocatore trovato, null se non trovato
	 */
	static function from_id($giocatore_id) {
		$giocatore_id = intval($giocatore_id);

		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$sql = "SELECT * FROM giocatori WHERE giocatori.giocatore_id = ?";
		$statement = $conn->prepare($sql);
		$statement->bind_param("i", $giocatore_id);
		$statement->execute();

		$result = $statement->get_result();
		if (!$result) {
			return null;
		} else if ($result->num_rows == 0) {
			return null;
		} else if ($result->num_rows > 1) {
			// ??
			return null;
		}

		$giocatore = $result->fetch_assoc();

		return Giocatore::from_assoc_array($giocatore);
	}

	/**
	 * Restituisce tutti i giocatori.
	 * @return null|Giocatore[] giocatori trovati, null se non trovato
	 */
	static function get_all() {
		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$sql = "SELECT * FROM giocatori";
		$query = $conn->query($sql);
		if (!$query) {
			return null;
		}

		$giocatori = array();
		while ($row = $query->fetch_assoc()) {
			$giocatori[] = Giocatore::from_assoc_array($row);
		}

		$conn->close();

		return $giocatori;
	}

	/**
	 * Cerca i giocatori in base ai parametri specificati.
	 * @param $posizione posizione del giocatore, null per non specificare
	 * @param $nazionalita nazionalità del giocatore, null per non specificare
	 * @param $cognome_nome cognome o nome del giocatore, null per non specificare
	 * @param $limit numero massimo di giocatori da restituire, null per non specificare
	 * @return Giocatore[] giocatori trovati
	 */
	static function cerca_giocatori($posizione, $nazionalita, $cognome_nome, $limit) {
		$limit = intval($limit);

		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$giocatori = array();

		$sql = "SELECT *".
			   " FROM giocatori".
			   " WHERE 1";

		if ($posizione != null) {
			$sql .= " AND giocatori.posizione = '$posizione'";
		}
		if ($nazionalita != null) {
			$sql .= " AND giocatori.giocatore_id IN (SELECT giocatore_id FROM nazionalita_giocatori WHERE nazione = '$nazionalita')";
		}
		if ($cognome_nome != null) {
			$sql .= " AND giocatori.cognome_nome LIKE '%$cognome_nome%'";
		}

		if ($limit != null) {
			$sql .= " LIMIT $limit";
		}

		$sql .= ";";

		$query = $conn->query($sql);
		if ($query) {
			while ($row = $query->fetch_assoc()) {
				$giocatori[] = Giocatore::from_assoc_array($row);
			}
		}

		$conn->close();

		return $giocatori;
	}

	/**
	 * Cerca i giocatori in base ai parametri specificati svincolati.
	 * @param $fantalega_id ID della fantalega
	 * @param $posizione posizione del giocatore, null per non specificare
	 * @param $nazionalita nazionalità del giocatore, null per non specificare
	 * @param $cognome_nome cognome o nome del giocatore, null per non specificare
	 * @param $limit numero massimo di giocatori da restituire, null per non specificare
	 * @return Giocatore[] giocatori trovati
	 */
	static function cerca_giocatori_svincolati($fantalega_id, $posizione, $nazionalita, $cognome_nome, $limit) {
		$fantalega_id = intval($fantalega_id);

		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$giocatori = array();

		$sql = "SELECT *".
			   " FROM giocatori".
			   " WHERE 1";

		if ($posizione != null) {
			$sql .= " AND giocatori.posizione = '$posizione'";
		}
		if ($nazionalita != null) {
			$sql .= " AND giocatori.giocatore_id IN (SELECT giocatore_id FROM nazionalita_giocatori WHERE nazione = '$nazionalita')";
		}
		if ($cognome_nome != null) {
			$sql .= " AND giocatori.cognome_nome LIKE '%$cognome_nome%'";
		}

		// Controlla la tabella giocatori_in_fantasquadre (fantasquadra_id, giocatore_id)
		$sql .= " AND giocatori.giocatore_id NOT IN (SELECT giocatore_id FROM giocatori_in_fantasquadre WHERE fantasquadra_id = $fantalega_id)";

		if ($limit != null) {
			$sql .= " LIMIT $limit";
		}

		$sql .= ";";

		$query = $conn->query($sql);
		if ($query) {
			while ($row = $query->fetch_assoc()) {
				$giocatori[] = Giocatore::from_assoc_array($row);
			}
		}

		$conn->close();

		return $giocatori;
	}
}
?>
