<html>

<head>
	<title>Aggiorna quadro</title>
</head>

<body>
	<?php
		if ($_SERVER['REQUEST_METHOD'] === 'POST') {
			$host = "localhost";
			$user = "root";
			$password = "";
			$db = "commesse";

			$connect = new mysqli($host, $user, $password, $db);
			if ($connect->connect_error) {
				exit("Errore connessione: " . $connect->connect_error);
			}

			$data_commessa = "STR_TO_DATE('".$_POST["data_commessa"]."', '%d/%m/%Y')";

			if (isset($_POST["idCommessa"])) {
				$sql = "UPDATE tblCommesse".
				       " SET data_commessa = ".$data_commessa.
				       ", descrizione = '".$_POST["descrizione"]."'".
				       ", idRichiedente = ".$_POST["idRichiedente"].
				       ", idReparto = ".$_POST["idReparto"].
				       ", idTipoIntervento = ".$_POST["idTipoIntervento"].
				       " WHERE idCommessa = ".$_POST["idCommessa"];
			} else {
				$sql = "INSERT INTO tblCommesse ".
				       "(".
				       "    data_commessa,".
					   "    descrizione,".
					   "    idRichiedente,".
					   "    idReparto,".
					   "    idTipoIntervento".
					   ")".
				       " VALUES (".
					   "    ".$data_commessa.",".
					   "    '".$_POST["descrizione"]."',".
					   "    ".$_POST["idRichiedente"].",".
					   "    ".$_POST["idReparto"].",".
					   "    ".$_POST["idTipoIntervento"].
					   ")";
			}

			$result = $connect->query($sql);

			header("location: view_commesse.php");

			$connect->close();
		} else {
			exit("Non si può invocare questa pagina senza parametri!");
		}
	?>
</body>

</html>
