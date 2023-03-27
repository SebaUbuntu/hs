<html>

<head>
	<title>Visualizza quadri</title>
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

		$sql = "SELECT tblTipoIntervento.descrizione,".
		       "       COUNT(tblCommesse.idCommessa) as numCommesse".
		       " FROM tblCommesse INNER JOIN tblTipoIntervento ON tblCommesse.idTipoIntervento = tblTipoIntervento.id".
		       " GROUP BY tblTipoIntervento.id;";
		$commesse = $connect->query($sql);

		print("<h1>Commesse per tipo intervento</h1>");

		if ($commesse->num_rows > 0) {
			echo "<table>";

			echo "<tr>";
			echo "<th>Tipo intervento</th>";
			echo "<th>Numero commesse</th>";
			echo "</tr>";

			while ($row = $commesse->fetch_assoc()) {
				echo "<tr>";
				echo "<td>".$row["descrizione"]."</td>";
				echo "<td>".$row["numCommesse"]."</td>";
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
