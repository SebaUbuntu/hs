<?php
require_once "base.php";
require_once __DIR__."/../database/database.php";

/**
 * Classe che rappresenta un invito ad una fantalega.
 */
class Invito extends Base {
	public $utente_id;
	public $fantalega_id;

	function __construct($utente_id, $fantalega_id) {
		$this->utente_id = $utente_id;
		$this->fantalega_id = $fantalega_id;
	}

	/**
	 * Elimina un invito.
	 */
	function elimina_invito() {
		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$sql = "DELETE FROM inviti".
		       " WHERE inviti.utente_id = $this->utente_id AND inviti.fantalega_id = $this->fantalega_id";
		$query = $conn->query($sql);
		if (!$query) {
			return false;
		}

		$conn->close();

		return true;
	}

	/**
	 * Restituisce tutte gli inviti.
	 * @return array un array di oggetti Invito
	 */
	static function get_all() {
		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$sql = "SELECT *".
		       " FROM inviti";
		$query = $conn->query($sql);
		if (!$query) {
			return null;
		}

		$inviti = array();
		while ($row = $query->fetch_assoc()) {
			$inviti[] = new Invito(
				$row['utente_id'],
				$row['fantalega_id']
			);
		}

		$conn->close();

		return $inviti;
	}

	/**
	 * Restituisce un invito dato l'ID dell'utente e l'ID della fantalega a cui è stato invitato.
	 * @param int $utente_id l'ID dell'utente
	 * @param int $fantalega_id l'ID della fantalega
	 * @return null|Invito l'invito, null se non esiste
	 */
	static function get_invito($utente_id, $fantalega_id) {
		$utente_id = intval($utente_id);
		$fantalega_id = intval($fantalega_id);

		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$sql = "SELECT *".
		       " FROM inviti".
		       " WHERE inviti.utente_id = ? AND inviti.fantalega_id = ?";
        $statement = $conn->prepare($sql);
        $statement->bind_param("ii", $utente_id, $fantalega_id);
        $statement->execute();

        $result = $statement->get_result();
		if (!$result) {
			return null;
		}

		if ($row = $result->fetch_assoc()) {
			$invito = new Invito(
				$row['utente_id'],
				$row['fantalega_id']
			);
		} else {
			$invito = null;
		}

		$conn->close();

		return $invito;	
	}

	/**
	 * Restituisce tutti gli inviti di un utente.
	 * @param int $utente_id l'id dell'utente
	 * @return array un array di oggetti Invito
	 */
	static function get_inviti_utente($utente_id) {
		$utente_id = intval($utente_id);

		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$sql = "SELECT *".
		       " FROM inviti".
		       " WHERE inviti.utente_id = ?";
        $statement = $conn->prepare($sql);
        $statement->bind_param("i", $utente_id);
        $statement->execute();

        $result = $statement->get_result();
		if (!$result) {
			return null;
		}

		$inviti = array();
		while ($row = $result->fetch_assoc()) {
			$inviti[] = new Invito(
				$row['utente_id'],
				$row['fantalega_id']
			);
		}

		$conn->close();

		return $inviti;
	}

	/**
	 * Restituisce tutti gli inviti ad una fantalega.
	 * @param int $fantalega_id l'id della fantalega
	 * @return array un array di oggetti Invito
	 */
	static function get_inviti_fantalega($fantalega_id) {
		$fantalega_id = intval($fantalega_id);

		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$sql = "SELECT *".
		       " FROM inviti".
		       " WHERE inviti.fantalega_id = ?";
        $statement = $conn->prepare($sql);
        $statement->bind_param("i", $fantalega_id);
        $statement->execute();

        $result = $statement->get_result();
		if (!$result) {
			return null;
		}

		$inviti = array();
		while ($row = $result->fetch_assoc()) {
			$inviti[] = new Invito(
				$row['utente_id'],
				$row['fantalega_id']
			);
		}

		$conn->close();

		return $inviti;
	}

	/**
	 * Crea un invito dato l'ID dell'utente e l'ID della fantalega a cui è stato invitato.
	 * @param int $utente_id l'ID dell'utente
	 * @param int $fantalega_id l'ID della fantalega
	 * @return null|Invito l'invito, null se non è stato creato
	 */
	static function crea_invito($utente_id, $fantalega_id) {
		$utente_id = intval($utente_id);
		$fantalega_id = intval($fantalega_id);

		$conn = Database::get_connection();
		if (!$conn) {
			return null;
		}

		$sql = "INSERT INTO inviti (utente_id, fantalega_id) VALUES (?, ?)";
        $statement = $conn->prepare($sql);
        $statement->bind_param("ii", $utente_id, $fantalega_id);
        $statement->execute();

		$conn->close();

		return new Invito(
			$utente_id,
			$fantalega_id
		);
	}
}
?>
