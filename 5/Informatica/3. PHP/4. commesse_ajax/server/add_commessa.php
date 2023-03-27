<?php
function add_commessa(
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

	$sql = "INSERT INTO tblcommesse (".
	       "    data_commessa,".
		   "    descrizione,".
		   "    idRichiedente,".
		   "    idReparto,".
		   "    idTipoIntervento".
		   ") VALUES (".
		   "    STR_TO_DATE('".$data."', '%Y-%m-%d'),".
		   "    '".$descrizione."',".
		   "    ".$id_richiedente.",".
		   "    ".$id_reparto.",".
		   "    ".$id_tipo_intervento.
		   ")";

	$result = $connect->query($sql);
	$error = $connect->error;

	$connect->close();

	return array(
		"result" => $result,
		"error" => $error,
	);
}
?>
