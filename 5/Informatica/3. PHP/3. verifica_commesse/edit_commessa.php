<html>

<head>
	<title>Modifica commessa</title>
</head>

<body>
	<?php
		if (isset($_GET["idCommessa"])) {
			$host = "localhost";
			$user = "root";
			$password = "";
			$db = "commesse";

			$connect = new mysqli($host, $user, $password, $db);
			if ($connect->connect_error) {
				exit("Errore connessione: " . $connect->connect_error);
			}

			if (isset($_GET["delete"]) && $_GET["delete"] == "1") {
				$connect->query("DELETE FROM tblCommesse WHERE idCommessa = '".$_GET["idCommessa"]."'");

				header("Location: view_commesse.php");
				exit();
			}

			$result = $connect->query("SELECT * FROM tblCommesse WHERE idCommessa = '".$_GET["idCommessa"]."'");
			$commessa = $result->fetch_assoc();

			$result = $connect->query("SELECT * FROM idUtente");
			$utenti = $result;

			$result = $connect->query("SELECT * FROM tblReparto");
			$reparti = $result;

			$result = $connect->query("SELECT * FROM tblTipoIntervento");
			$tipi_intervento = $result;
		}
		else {
			exit("Non si può invocare questa pagina senza parametri!");
		}
	?>

	<h1>Modifica commessa <?php print($commessa["idCommessa"]); ?></h1>

	<form id="formEdit" name="formEdit" method="post" action="update_commessa.php">
		<input type="hidden" name="idCommessa" id="idCommessa" value='<?php echo $commessa["idCommessa"]; ?>'/>

		<label for="data_commessa">Data commessa (gg/mm/yyyy)</label>
		<input type="text" name="data_commessa" id="data_commessa" value='<?php echo $commessa["data_commessa"]; ?>'/>
        <br>

        <label for="descrizione">Descrizione</label>
		<input type="text" name="descrizione" id="descrizione" value='<?php echo $commessa["descrizione"]; ?>'/>
        <br>

		<label for="idRichiedente">Richiedente</label>
		<select id="idRichiedente" name="idRichiedente">
			<?php
				while ($utente = $utenti->fetch_assoc()) {
					$selected = $commessa["idRichiedente"] == $utente["idUtente"];
					print("<option value='".$utente["idUtente"]."'");
					if ($selected) {
						print(" selected");
					}
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
					$selected = $commessa["idReparto"] == $reparto["idReparto"];
					print("<option value='".$reparto["idReparto"]."'");
					if ($selected) {
						print(" selected");
					}
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
					$selected = $commessa["idTipoIntervento"] == $tipo_intervento["id"];
					print("<option value='".$tipo_intervento["id"]."'");
					if ($selected) {
						print(" selected");
					}
					print(">");
					print($tipo_intervento["descrizione"]);
					print("</option>");
				}
			?>
		</select>
		<br>

		<input type="submit" name="salva" id="salva" value="Salva" />
	</form>
</body>

</html>
