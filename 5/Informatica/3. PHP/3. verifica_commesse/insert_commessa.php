<!DOCTYPE html>
<html>

<head>
    <title>Aggiungi commessa</title>
</head>

<body>
    <?php
		$host = "localhost";
		$user = "root";
		$password = "";
		$db = "commesse";

		$connect = new mysqli($host, $user, $password, $db);
		if ($connect->connect_error) {
			exit("Errore connessione: " . $connect->connect_error);
		}

        $result = $connect->query("SELECT * FROM idUtente");
        $utenti = $result;

        $result = $connect->query("SELECT * FROM tblReparto");
        $reparti = $result;

        $result = $connect->query("SELECT * FROM tblTipoIntervento");
        $tipi_intervento = $result;
	?>

    <h1>Aggiungi commessa</h1>
    <form action="update_commessa.php" method="post">
        <label for="data_commessa">Data commessa (gg/mm/yyyy)</label>
        <input type="text" name="data_commessa" id="data_commessa" required>
        <br>

        <label for="descrizione">Descrizione</label>
        <input type="text" name="descrizione" id="descrizione" required>
        <br>

		<label for="idRichiedente">Richiedente</label>
		<select id="idRichiedente" name="idRichiedente">
			<?php
				while ($utente = $utenti->fetch_assoc()) {
					//$selected = $commessa["idRichiedente"] == $utente["idUtente"];
					print("<option value='".$utente["idUtente"]."'");
					//if ($selected) {
					//	print(" selected");
					//}
					print(">");
					print($utente["nominativo"]);
					print("</option>");
				}
			?>
		</select>
		<br>

		<label for="idReparto">Reparto</label>
		<select id="idReparto" name="idReparto">
			<?php
				while ($reparto = $reparti->fetch_assoc()) {
					//$selected = $commessa["idReparto"] == $reparto["idReparto"];
					print("<option value='".$reparto["idReparto"]."'");
					//if ($selected) {
					//	print(" selected");
					//}
					print(">");
					print($reparto["descrizione"]);
					print("</option>");
				}
			?>
		</select>
		<br>

		<label for="idTipoIntervento">Tipo intervento</label>
		<select id="idTipoIntervento" name="idTipoIntervento">
			<?php
				while ($tipo_intervento = $tipi_intervento->fetch_assoc()) {
					//$selected = $commessa["idTipoIntervento"] == $tipo_intervento["id"];
					print("<option value='".$tipo_intervento["id"]."'");
					//if ($selected) {
					//	print(" selected");
					//}
					print(">");
					print($tipo_intervento["descrizione"]);
					print("</option>");
				}
			?>
		</select>
		<br>

        <input type="submit" value="Aggiungi">
    </form>
</body>

</html>
