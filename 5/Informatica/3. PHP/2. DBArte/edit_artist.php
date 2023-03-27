<html>

<head>
	<title>Modifica artista</title>
</head>

<body>
	<?php
		if (isset($_GET["AR_CodiceArtista"])) {
			$host = "localhost";
			$user = "root";
			$password = "";
			$db = "arte";

			$connect = new mysqli($host, $user, $password, $db);
			if ($connect->connect_error) {
				exit("Errore connessione: " . $connect->connect_error);
			}

			if (isset($_GET["delete"]) && $_GET["delete"] == "1") {
				$connect->query("DELETE FROM quadri WHERE QQ_CodiceArtista = '".$_GET["AR_CodiceArtista"]."'");

				$connect->query("DELETE FROM artisti WHERE AR_CodiceArtista = '".$_GET["AR_CodiceArtista"]."'");

				header("Location: view_artists.php");
				exit();
			}

			$result = $connect->query("SELECT * FROM artisti WHERE AR_CodiceArtista = '".$_GET["AR_CodiceArtista"]."'");
			$artist = $result->fetch_assoc();
		}
		else {
			exit("Non si può invocare questa pagina senza parametri!");
		}
	?>

	<h1>Modifica artista <?php print($artist["AR_CodiceArtista"]); ?></h1>

	<form id="formEdit" name="formEdit" method="post" action="update_artist.php">
		<input type="hidden" name="AR_CodiceArtista" id="AR_CodiceArtista" value='<?php echo $artist["AR_CodiceArtista"]; ?>'/>

		<label for="AR_Nome">Nome</label>
		<input type="text" name="AR_Nome" id="AR_Nome" value='<?php echo $artist["AR_Nome"]; ?>'/>
		<br>

		<label for="AR_Alias">Alias</label>
		<input type="text" name="AR_Alias" id="AR_Alias" value='<?php echo $artist["AR_Alias"]; ?>'/>
		<br>

		<label for="AR_DataNascita">Data di nascita</label>
		<input type="text" name="AR_DataNascita" id="AR_DataNascita" value='<?php echo $artist["AR_DataNascita"]; ?>'/>
		<br>

		<label for="AR_DataMorte">Data di morte</label>
		<input type="text" name="AR_DataMorte" id="AR_DataMorte" value='<?php echo $artist["AR_DataMorte"]; ?>'/>
		<br>

		<input type="submit" name="salva" id="salva" value="Salva" />
	</form>
</body>

</html>
