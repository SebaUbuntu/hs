<?php
require_once "base.php";
require_once __DIR__."/../database/database.php";
require_once "invito.php";
require_once "utente.php";
require_once "fantasquadra.php";

/**
 * Classe che rappresenta una fantalega.
 */
class Fantalega extends Base {
	public $fantalega_id;
	public $nome;
	public $admin_id;
	public $crediti_iniziali; 
	public $numero_portieri;   
	public $numero_difensori;
	public $numero_centrocampisti;
	public $numero_attaccanti;

	function __construct(
		$fantalega_id,
		$nome,
		$admin_id,
		$crediti_iniziali, 
		$numero_portieri,
		$numero_difensori,
		$numero_centrocampisti,
		$numero_attaccanti
	) {
		$this->fantalega_id = $fantalega_id;
		$this->nome = $nome;
		$this->admin_id = $admin_id;
		$this->crediti_iniziali = $crediti_iniziali;
		$this->numero_portieri = $numero_portieri;
		$this->numero_difensori = $numero_difensori;
		$this->numero_centrocampisti = $numero_centrocampisti;
		$this->numero_attaccanti = $numero_attaccanti;
	}

	/**
	 * Ottieni informazioni sull'utente admin della fantalega.
	 * @return null|Utente utente admin della fantalega, null se non trovato
	 */
	function get_utente_admin() {
		return Utente::from_id($this->admin_id);
	}

	/**
	 * Restituisce la lista di fantasquadre della fantalega.
	 * @return null|Fantasquadra[] lista di fantasquadre della fantalega, null se non trovata
	 */
	function get_fantasquadre() {
		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$sql = "SELECT fantasquadre.*".
			   " FROM fantasquadre".
			   " WHERE fantasquadre.fantalega_id = ?";
		$statement = $conn->prepare($sql);
		$statement->bind_param("i", $this->fantalega_id);
		$statement->execute();

		$result = $statement->get_result();
		if (!$result) {
			return null;
		}

		$fantasquadre = array();
		while ($fantasquadra = $result->fetch_assoc()) {
			$fantasquadre[] = Fantasquadra::from_assoc_array($fantasquadra);
		}

		$conn->close();

		return $fantasquadre;
	}

	/**
	 * Crea una nuova fantasquadra nella fantalega.
	 * @param string $nome nome della fantasquadra
	 * @param int $utente_id id dell'utente che crea la fantasquadra
	 * @return null|Fantasquadra oggetto Fantasquadra corrispondente, null se non è stato possibile creare la fantasquadra
	 */
	function crea_fantasquadra($nome, $utente_id) {
		return Fantasquadra::crea_fantasquadra($nome, $this->fantalega_id, $utente_id, $this->crediti_iniziali);
	}

	/**
	 * Elimina la fantalega dal database
	 * @return bool true se l'eliminazione è andata a buon fine, false altrimenti
	 */
	function elimina() {
		$conn = Database::get_connection();
		if (!$conn) {
			return false;
		}

		$sql = "DELETE FROM fantaleghe WHERE fantalega_id = ?";
		$statement = $conn->prepare($sql);
		$statement->bind_param("i", $this->fantalega_id);
		$statement->execute();

		$conn->close();

		return true;
	}

	/**
	 * Restituisce l'oggetto Fantalega dato un array associativo.
	 * @param array $assoc_array array associativo con i dati della fantalega
	 * @return Fantalega oggetto Fantalega corrispondente
	 */
	static function from_assoc_array($array) {
		return new Fantalega(
			$array["fantalega_id"],
			$array["nome"],
			$array["admin_id"],
			$array["crediti_iniziali"],
			$array["numero_portieri"],
			$array["numero_difensori"],
			$array["numero_centrocampisti"],
			$array["numero_attaccanti"]
		);
	}

	/**
	 * Restituisce l'oggetto Fantalega dato un ID fantalega.
	 * @param int $fantalega_id ID della fantalega
	 * @return null|Fantalega oggetto Fantalega corrispondente, null se non trovato
	 */
	static function from_id($fantalega_id) {
		$fantalega_id = intval($fantalega_id);

		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$sql = "SELECT fantaleghe.*".
			   " FROM fantaleghe".
			   " WHERE fantalega_id = ?";
		$statement = $conn->prepare($sql);
		$statement->bind_param("i", $fantalega_id);
		$statement->execute();

		$result = $statement->get_result();
		if (!$result) {
			return null;
		}
		if ($result->num_rows != 1) {
			return null;
		}

		$fantalega = $result->fetch_assoc();

		$conn->close();

		return Fantalega::from_assoc_array($fantalega);
	}

	/**
	 * Crea la fantalega nel database e restituisce l'oggetto Fantalega corrispondente.
	 * @param string $nome nome della fantalega 
	 * @param int $admin_id id dell'utente admin della fantalega
	 * @return null|Fantalega oggetto Fantalega corrispondente, null se non è stato possibile creare la fantalega
	 */
	static function crea_fantalega(
		$nome,
		$admin_id,
		$crediti_iniziali,
		$numero_portieri,
		$numero_difensori,
		$numero_centrocampisti,
		$numero_attaccanti
	) {
		$admin_id = intval($admin_id);
		$crediti_iniziali = intval($crediti_iniziali);
		$numero_portieri = intval($numero_portieri);
		$numero_difensori = intval($numero_difensori);
		$numero_centrocampisti = intval($numero_centrocampisti);
		$numero_attaccanti = intval($numero_attaccanti);

		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		// Controlla che non esista già una fantalega con lo stesso nome
		$sql = "SELECT fantaleghe.*".
			   " FROM fantaleghe".
			   " WHERE fantaleghe.nome = ?";
		$statement = $conn->prepare($sql);
		$statement->bind_param("s", $nome);
		$statement->execute();

		$result = $statement->get_result();
		if (!$result) {
			return null;
		}
		if ($result->num_rows > 0) {
			return null;
		}

		$sql = "INSERT INTO fantaleghe (nome, admin_id, crediti_iniziali, numero_portieri, numero_difensori, numero_centrocampisti, numero_attaccanti)".
			   " VALUES (?, ?, ?, ?, ?, ?, ?)";
		$statement = $conn->prepare($sql);
		$statement->bind_param("siiiiii", $nome, $admin_id, $crediti_iniziali, $numero_portieri, $numero_difensori, $numero_centrocampisti, $numero_attaccanti);
		$statement->execute();

		$fantalega_id = $conn->insert_id;

		$conn->close();

		return Fantalega::from_id($fantalega_id);
	}
}
?>
