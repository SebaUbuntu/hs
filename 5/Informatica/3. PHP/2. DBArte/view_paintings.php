<html>

<head>
	<title>Visualizza quadri</title>
</head>

<body>
	<?php
		$host = "localhost";
		$user = "root";
		$password = "";
		$db = "arte";

		$connect = new mysqli($host, $user, $password, $db);
		if ($connect->connect_error) {
			exit("Errore connessione: " . $connect->connect_error);
		}

		if (isset($_GET["AR_CodiceArtista"])) {
			$sql = "SELECT *".
			       " FROM artisti".
			       " WHERE AR_CodiceArtista = '".$_GET["AR_CodiceArtista"]."'";
			$result = $connect->query($sql);

			if ($result->num_rows != 1) {
				exit("Artista non trovato");
			}

			$artista = $result->fetch_assoc();
			print("<h1>Quadri di ".$artista["AR_Nome"]."</h1>");

			$sql = "SELECT *".
			       " FROM quadri".
			       " WHERE QQ_CodiceArtista = '".$_GET["AR_CodiceArtista"]."'";
			$result = $connect->query($sql);
		} else {
			print("<h1>Quadri di tutti gli artisti</h1>");

			$sql = "SELECT *".
			       " FROM quadri";
			$result = $connect->query($sql);
		}

		if ($result->num_rows > 0) {
			echo "<table>";

			echo "<tr>";
			echo "<th>Titolo quadro</th>";
			echo "<th>Codice artista</th>";
			echo "<th>Anno esecuzione</th>";
			echo "<th>Tecnica</th>";
			echo "<th>Altezza</th>";
			echo "<th>Larghezza</th>";
			echo "<th>Codice museo</th>";
			echo "<th>Note</th>";
			echo "</tr>";

			while ($row = $result->fetch_assoc()) {
				echo "<tr>";
				echo "<td>".$row["QQ_TitoloQuadro"]."</td>";
				echo "<td>".$row["QQ_CodiceArtista"]."</td>";
				echo "<td>".$row["QQ_AnnoEsecuzione"]."</td>";
				echo "<td>".$row["QQ_Tecnica"]."</td>";
				echo "<td>".$row["QQ_Altezza"]."</td>";
				echo "<td>".$row["QQ_Larghezza"]."</td>";
				echo "<td>".$row["QQ_CodiceMuseo"]."</td>";
				echo "<td>".$row["QQ_Note"]."</td>";
				echo "<td><a href='edit_painting.php?QQ_TitoloQuadro=".$row["QQ_TitoloQuadro"]."'>Modifica</a></td>";
				echo "<td><a href='edit_painting.php?QQ_TitoloQuadro=".$row["QQ_TitoloQuadro"]."&delete=1'>Elimina</a></td>";
				echo "</tr>";
			}

			echo "</table>";
		} else {
			echo "0 risultati";
		}
		
		$connect->close();
	?>
</body>

</html>
