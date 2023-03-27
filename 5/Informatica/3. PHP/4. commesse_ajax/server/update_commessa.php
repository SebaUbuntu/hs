<?php
function update_commessa(
	$id_commessa,
	$data,
	$descrizione,
	$id_richiedente,
	$id_reparto,
	$id_tipo_intervento
) {
	require "database_info.php";

	$connect = new mysqli($host, $user, $password, $db);
	if ($connect->connect_error) {
		exit("Errore connessione: " . $connect->connect_error);
	}

	$sql = "UPDATE tblCommesse".
	       " SET data_commessa = STR_TO_DATE('".$data."', '%Y-%m-%d'),".
	       "     descrizione = '".$descrizione."',".
	       "     idRichiedente = ".$id_richiedente.",".
	       "     idReparto = ".$id_reparto.",".
	       "     idTipoIntervento = ".$id_tipo_intervento.
	       " WHERE idCommessa = ".$id_commessa;

	$result = $connect->query($sql);
	$error = $connect->error;

	$connect->close();

	return array(
		"result" => $result,
		"error" => $error,
	);
}
?>
