<?php
function get_commesse_per_tipo_intervento() {
	require "database_info.php";

	$result = array();

	$connect = new mysqli($host, $user, $password, $db);
	if ($connect->connect_error) {
		return $result;
	}

	$sql = "SELECT tblTipoIntervento.descrizione,".
	       "       COUNT(tblCommesse.idCommessa) as numCommesse".
	       " FROM tblCommesse INNER JOIN tblTipoIntervento ON tblCommesse.idTipoIntervento = tblTipoIntervento.id".
	       " GROUP BY tblTipoIntervento.id;";
	$commesse = $connect->query($sql);

	if ($commesse->num_rows > 0) {
		while ($row = $commesse->fetch_assoc()) {
			$commessa = array(
				"descrizione" => $row["descrizione"],
				"num_commesse" => $row["numCommesse"],
			);
			$result[] = $commessa;
		}
	}

	$connect->close();

	return $result;
}
?>
