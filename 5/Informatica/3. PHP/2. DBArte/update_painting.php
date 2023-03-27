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
			$db = "arte";

			$connect = new mysqli($host, $user, $password, $db);
			if ($connect->connect_error) {
				exit("Errore connessione: " . $connect->connect_error);
			}

			$sql = "UPDATE quadri".
			       " SET QQ_CodiceArtista = '".$_POST["QQ_CodiceArtista"]."'".
				   ", QQ_CodiceArtista = '".$_POST["QQ_CodiceArtista"]."'".
				   ", QQ_AnnoEsecuzione = '".$_POST["QQ_AnnoEsecuzione"]."'".
				   ", QQ_Tecnica = '".$_POST["QQ_Tecnica"]."'".
				   ", QQ_Altezza = '".$_POST["QQ_Altezza"]."'".
				   ", QQ_Larghezza = '".$_POST["QQ_Larghezza"]."'".
				   ", QQ_CodiceMuseo = '".$_POST["QQ_CodiceMuseo"]."'".
				   " WHERE QQ_TitoloQuadro = '".$_POST["QQ_TitoloQuadro"]."'";
			$result = $connect->query($sql);

			header("location: view_paintings.php?AR_CodiceArtista=".$_POST["QQ_CodiceArtista"]);

			$connect->close();
		} else {
			exit("Non si può invocare questa pagina senza parametri!");
		}
	?>
</body>

</html>
