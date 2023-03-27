<?php
function get_commesse() {
	require "database_info.php";

	$result = array();

	$connect = new mysqli($host, $user, $password, $db);
	if ($connect->connect_error) {
		return $result;
	}

	$sql = "SELECT tblCommesse.idCommessa,".
	       "       tblCommesse.data_commessa,".
	       "       tblCommesse.descrizione,".
	       "       idUtente.nominativo as richiedenteNominativo,".
	       "       idUtente.ruolo as richiedenteRuolo,".
	       "       tblReparto.descrizione as repartoDescrizione,".
	       "       tblTipoIntervento.descrizione as tipoInterventoDescrizione".
	       " FROM tblCommesse INNER JOIN idUtente ON tblCommesse.idRichiedente = idUtente.idUtente".
	       "                  INNER JOIN tblReparto ON tblCommesse.idReparto = tblReparto.idReparto".
	       "                  INNER JOIN tblTipoIntervento ON tblCommesse.idTipoIntervento = tblTipoIntervento.id;";
	$commesse = $connect->query($sql);

	if ($commesse->num_rows > 0) {
		while ($row = $commesse->fetch_assoc()) {
			$commessa = array(
				"id_commessa" => $row["idCommessa"],
				"data_commessa" => $row["data_commessa"],
				"descrizione" => $row["descrizione"],
				"richiedente_nominativo" => $row["richiedenteNominativo"],
				"richiedente_ruolo" => $row["richiedenteRuolo"],
				"reparto_descrizione" => $row["repartoDescrizione"],
				"tipo_intervento_descrizione" => $row["tipoInterventoDescrizione"],
			);
			$result[] = $commessa;
		}
	}

	$connect->close();

	return $result;
}
?>
