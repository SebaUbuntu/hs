<html>

<head>
	<title>Aggiungi artista</title>
</head>

<body>
	<?php
		if ($_SERVER['REQUEST_METHOD'] === 'POST') {
			$host = "localhost";
			$user = "root";
			$password = "";
			$db = "arte";
			$connect = new mysqli($host, $user, $password, $db);
			if ($connect->connect_error)
				exit("Errore connessione: " . $connect->connect_error);

			$sql = "INSERT INTO artisti (AR_CodiceArtista, AR_Nome, AR_Alias, AR_DataNascita, AR_DataMorte) VALUES ('".$_POST["AR_CodiceArtista"]."','".$_POST["AR_Nome"]."','".$_POST["AR_Alias"]."','".$_POST["AR_DataNascita"]."','".$_POST["AR_DataMorte"]."')";
			$result = $connect->query($sql);

			header("location: view_artists.php");
			$connect->close();
		}
		else {
			exit("Non si può invocare questa pagina senza parametri!");
		}
	?>
</body>

</html>
