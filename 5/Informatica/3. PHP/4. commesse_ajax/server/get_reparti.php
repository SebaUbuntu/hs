<?php
function get_reparti() {
	require "database_info.php";

	$result = array();

	$connect = new mysqli($host, $user, $password, $db);
	if ($connect->connect_error) {
		return $result;
	}

	$sql = "SELECT * FROM tblreparto";
	$reparti = $connect->query($sql);

	if ($reparti->num_rows > 0) {
		while ($row = $reparti->fetch_assoc()) {
			$reparto = array(
				"id" => $row["idReparto"],
				"descrizione" => $row["descrizione"],
			);
			$result[] = $reparto;
		}
	}

	$connect->close();

	return $result;
}
?>
