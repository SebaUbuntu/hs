<html>

<head>
	<title>Modifica artista</title>
</head>

<body>
	<?php
		if (isset($_GET["QQ_TitoloQuadro"])) {
			$host = "localhost";
			$user = "root";
			$password = "";
			$db = "arte";

			$connect = new mysqli($host, $user, $password, $db);
			if ($connect->connect_error) {
				exit("Errore connessione: " . $connect->connect_error);
			}

			if (isset($_GET["delete"]) && $_GET["delete"] == "1") {
				$connect->query("DELETE FROM quadri WHERE QQ_TitoloQuadro = '".$_GET["QQ_TitoloQuadro"]."'");

				header("Location: view_artists.php");
				exit();
			}

			$result = $connect->query("SELECT * FROM quadri WHERE QQ_TitoloQuadro = '".$_GET["QQ_TitoloQuadro"]."'");
			$painting = $result->fetch_assoc();

			$result = $connect->query("SELECT * FROM artisti");
			$artists = $result;

			$result = $connect->query("SELECT * FROM musei");
			$museums = $result;
		}
		else {
			exit("Non si può invocare questa pagina senza parametri!");
		}
	?>

	<h1>Modifica quadro <?php print($painting["QQ_TitoloQuadro"]); ?></h1>

	<form id="formEdit" name="formEdit" method="post" action="update_painting.php">
		<input type="hidden" name="QQ_TitoloQuadro" id="QQ_TitoloQuadro" value='<?php echo $painting["QQ_TitoloQuadro"]; ?>'/>

		<label for="QQ_CodiceArtista">Artista</label>
		<select id="QQ_CodiceArtista" name="QQ_CodiceArtista">
			<?php
				while ($artist = $artists->fetch_assoc()) {
					$selected = $painting["QQ_CodiceArtista"] == $artist["AR_CodiceArtista"];
					print("<option value='".$artist["AR_CodiceArtista"]."'");
					if ($selected) {
						print(" selected");
					}
					print(">");
					print($artist["AR_Nome"]);
					print("</option>");
				}
			?>
		</select>
		<br>

		<label for="QQ_AnnoEsecuzione">Anno esecuzione</label>
		<input type="text" name="QQ_AnnoEsecuzione" id="QQ_AnnoEsecuzione" value='<?php echo $painting["QQ_AnnoEsecuzione"]; ?>'/>
		<br>

		<label for="QQ_Tecnica">Tecnica</label>
		<input type="text" name="QQ_Tecnica" id="QQ_Tecnica" value='<?php echo $painting["QQ_Tecnica"]; ?>'/>
		<br>

		<label for="QQ_Altezza">Altezza</label>
		<input type="text" name="QQ_Altezza" id="QQ_Altezza" value='<?php echo $painting["QQ_Altezza"]; ?>'/>
		<br>

		<label for="QQ_Larghezza">Larghezza</label>
		<input type="text" name="QQ_Larghezza" id="QQ_Larghezza" value='<?php echo $painting["QQ_Larghezza"]; ?>'/>
		<br>

		<label for="QQ_CodiceMuseo">Museo</label>
		<select id="QQ_CodiceMuseo" name="QQ_CodiceMuseo">
			<?php
				while ($museum = $museums->fetch_assoc()) {
					$selected = $painting["QQ_CodiceMuseo"] == $museum["MM_CodiceMuseo"];
					print("<option value='".$museum["MM_CodiceMuseo"]."'");
					if ($selected) {
						print(" selected");
					}
					print(">");
					print($museum["MM_Nome"]);
					print("</option>");
				}
			?>
		</select>
		<br>

		<input type="submit" name="salva" id="salva" value="Salva" />
	</form>
</body>

</html>
