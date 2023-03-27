<?php
function get_tipi_intervento() {
	require "database_info.php";

	$result = array();

	$connect = new mysqli($host, $user, $password, $db);
	if ($connect->connect_error) {
		return $result;
	}

	$sql = "SELECT * FROM tblTipoIntervento";
	$tipi_intervento = $connect->query($sql);

	if ($tipi_intervento->num_rows > 0) {
		while ($row = $tipi_intervento->fetch_assoc()) {
			$tipo_intervento = array(
				"id" => $row["id"],
				"descrizione" => $row["descrizione"],
			);
			$result[] = $tipo_intervento;
		}
	}

	$connect->close();

	return $result;
}
?>
