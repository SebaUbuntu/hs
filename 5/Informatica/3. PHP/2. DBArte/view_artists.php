<html>

<head>
	<title>Visualizza artisti</title>
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

		$sql = "SELECT *".
		       " FROM artisti";
		$result = $connect->query($sql);

		if ($result->num_rows > 0) {
			echo "<table>";

			echo "<tr>";
			echo "<th>Nome</th>";
			echo "<th>Alias</th>";
			echo "<th>Data nascita</th>";
			echo "<th>Data morte</th>";
			echo "</tr>";

			while ($row = $result->fetch_assoc()) {
				echo "<tr>";
				echo "<td>".$row["AR_Nome"]."</td>";
				echo "<td>".$row["AR_Alias"]."</td>";
				echo "<td>".$row["AR_DataNascita"]."</td>";
				echo "<td>".$row["AR_DataMorte"]."</td>";
				echo "<td><a href='view_paintings.php?AR_CodiceArtista=".$row["AR_CodiceArtista"]."'>Visualizza quadri</a></td>";
				echo "<td><a href='edit_artist.php?AR_CodiceArtista=".$row["AR_CodiceArtista"]."'>Modifica</a></td>";
				echo "<td><a href='edit_artist.php?AR_CodiceArtista=".$row["AR_CodiceArtista"]."&delete=1'>Elimina</a></td>";
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
