<?php
function get_utenti() {
	require "database_info.php";

	$result = array();

	$connect = new mysqli($host, $user, $password, $db);
	if ($connect->connect_error) {
		return $result;
	}

	$sql = "SELECT * FROM idutente";
	$utenti = $connect->query($sql);

	if ($utenti->num_rows > 0) {
		while ($row = $utenti->fetch_assoc()) {
			$utente = array(
				"id" => $row["idUtente"],
				"nominativo" => $row["nominativo"],
				"ruolo" => $row["ruolo"],
			);
			$result[] = $utente;
		}
	}

	$connect->close();

	return $result;
}
?>
