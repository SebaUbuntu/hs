<html>

<head>
	<title>Aggiorna artista</title>
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

			$sql = "UPDATE artisti".
			       " SET AR_Nome = '".$_POST["AR_Nome"]."'".
				   ", AR_Alias = '".$_POST["AR_Alias"]."'".
				   ", AR_DataNascita = '".$_POST["AR_DataNascita"]."'".
				   ", AR_DataMorte = '".$_POST["AR_DataMorte"]."'".
				   " WHERE AR_CodiceArtista = '".$_POST["AR_CodiceArtista"]."'";
			$result = $connect->query($sql);

			header("location: view_artists.php");

			$connect->close();
		} else {
			exit("Non si può invocare questa pagina senza parametri!");
		}
	?>
</body>

</html>
