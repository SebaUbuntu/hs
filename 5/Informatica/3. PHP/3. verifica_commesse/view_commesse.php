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

		$sql = "SELECT tblCommesse.idCommessa,".
		       "       tblCommesse.data_commessa,".
		       "       tblCommesse.descrizione,".
		       "       idUtente.nominativo as richiedenteNominativo,".
		       "       idUtente.ruolo as richiedenteRuolo,".
		       "       tblReparto.descrizione as repartoDescrizione,".
		       "       tblTipoIntervento.descrizione as tipoInterventoDescrizione".
		       " FROM tblCommesse INNER JOIN idUtente ON tblCommesse.idRichiedente = idUtente.idUtente".
		       "                  INNER JOIN tblReparto ON tblCommesse.idReparto = tblReparto.idReparto".
		       "                  INNER JOIN tblTipoIntervento ON tblCommesse.idTipoIntervento = tblTipoIntervento.id;";
		$commesse = $connect->query($sql);

		print("<h1>Commesse</h1>");

		if ($commesse->num_rows > 0) {
			echo "<table>";

			echo "<tr>";
			echo "<th>Data commessa</th>";
			echo "<th>Descrizione</th>";
			echo "<th>Richiedente</th>";
			echo "<th>Reparto</th>";
			echo "<th>Tipo intervento</th>";
			echo "</tr>";

			while ($row = $commesse->fetch_assoc()) {
				echo "<tr>";
				echo "<td>".$row["data_commessa"]."</td>";
				echo "<td>".$row["descrizione"]."</td>";
				echo "<td>".$row["richiedenteNominativo"]." (".$row["richiedenteRuolo"].")</td>";
				echo "<td>".$row["repartoDescrizione"]."</td>";
				echo "<td>".$row["tipoInterventoDescrizione"]."</td>";
				echo "<td><a href='edit_commessa.php?idCommessa=".$row["idCommessa"]."'>Modifica</a></td>";
				echo "<td><a href='edit_commessa.php?idCommessa=".$row["idCommessa"]."&delete=1'>Elimina</a></td>";
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
