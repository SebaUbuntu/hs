<?php
function get_commessa($commessa_id) {
	require "database_info.php";

	$result = array();

	$connect = new mysqli($host, $user, $password, $db);
	if ($connect->connect_error) {
		return $result;
	}

	$sql = "SELECT tblCommesse.idCommessa,".
	       "       tblCommesse.data_commessa,".
	       "       tblCommesse.descrizione,".
	       "       tblCommesse.idRichiedente,".
	       "       tblCommesse.idReparto,".
	       "       tblCommesse.idTipoIntervento".
	       " FROM tblCommesse".
	       " WHERE tblCommesse.idCommessa = ".$commessa_id.";";
	$commessa = $connect->query($sql);

	if ($commessa->num_rows == 1) {
		$row = $commessa->fetch_assoc();
		$commessa = array(
			"id" => $row["idCommessa"],
			"data" => $row["data_commessa"],
			"descrizione" => $row["descrizione"],
			"id_richiedente" => $row["idRichiedente"],
			"id_reparto" => $row["idReparto"],
			"id_tipo_intervento" => $row["idTipoIntervento"],
		);
		$result = $commessa;
	}

	$connect->close();

	return $result;
}
?>
