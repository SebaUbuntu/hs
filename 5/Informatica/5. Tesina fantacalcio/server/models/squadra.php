<?php
require_once "base.php";
require_once "giocatore.php";
require_once __DIR__."/../database/database.php";

/**
 * Classe che rappresenta una squadra di Serie A TIM.
 */
class Squadra extends Base {
	public $squadra_id;
	public $nome;
	public $citta;
	public $anno_fondazione;

	function __construct(
		$squadra_id,
		$nome,
		$citta,
		$anno_fondazione
	) {
		$this->squadra_id = $squadra_id;
		$this->nome = $nome;
		$this->citta = $citta;
		$this->anno_fondazione = $anno_fondazione;
	}

	/**
	 * Restituisce tutti i giocatori di una squadra
	 * @return null|Giocatore[] array di giocatori, null in caso di errore 
	 */
	function get_giocatori() {
		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$sql = "SELECT giocatori.*".
			   " FROM giocatori".
			   " WHERE giocatori.squadra_id = ?";
		$statement = $conn->prepare($sql);
		$statement->bind_param("i", $this->squadra_id);
		$statement->execute();

		$result = $statement->get_result();
		if (!$result) {
			return null;
		}

		$giocatori = array();

		while ($row = $result->fetch_assoc()) {
			$giocatori[] = Giocatore::from_assoc_array($row);
		}

		$conn->close();

		return $giocatori;
	}

	/**
	 * Restituisce una squadra dato un array associativo.
	 * @param $array array associativo con i dati della squadra
	 * @return Squadra squadra creata
	 */
	static function from_assoc_array($array) {
		return new Squadra(
			$array["squadra_id"],
			$array["nome"],
			$array["citta"],
			$array["anno_fondazione"]
		);
	}

	/**
	 * Restituisce una squadra dato il suo id.
	 * @param $squadra_id id della squadra da cercare
	 * @return null|Squadra squadra trovato, null se non trovata
	 */
	static function from_id($squadra_id) {
		$squadra_id = intval($squadra_id);

		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$sql = "SELECT squadre.* FROM squadre WHERE squadre.squadra_id = ?";
		$statement = $conn->prepare($sql);
		$statement->bind_param("i", $squadra_id);
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

		$squadra = $result->fetch_assoc();

		$conn->close();

		return new Squadra(
			$squadra["squadra_id"],
			$squadra["nome"],
			$squadra["citta"],
			$squadra["anno_fondazione"]
		);
	}

	/**
	 * restituisce tutte le squadre presenti nel database
	 * @return null|Squadra[] array di squadre, null in caso di errore
	 */
	static function get_all() {
		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$sql = "SELECT squadre.* FROM squadre";
		$query = $conn->query($sql);
		if (!$query) {
			return null;
		}

		$squadre = array();

		while ($row = $query->fetch_assoc()) {
			$squadre[] = Squadra::from_assoc_array($row);
		}

		$conn->close();

		return $squadre;
	}
}
?>
