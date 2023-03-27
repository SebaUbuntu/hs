<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Modifica dati con MySQLi</title>
</head>

<body>
<?php
	$host = "localhost";
	$user = "root";
	$password = "";
	$db = "motociclismo";

	$connect = new mysqli($host, $user, $password, $db);
	if ($connect->connect_error) {
		exit("Errore connessione: " . $connect->connect_error);
	} 

	$sql = "SELECT * FROM moto";
	$result = $connect->query($sql);

	if ($result->num_rows > 0) {
		echo "<table><tr><th>Marca</th><th>Cilindrata</th></tr>";
		while($row = $result->fetch_object()) {  // approccio ai risultati come oggetti
			echo "<tr>";
			echo "<td>" . $row->marca . "</td>";
			echo "<td>" . $row->cilindrata . "</td>";
			echo "<td><a href='edit.php?codMoto=" . $row->codMoto . "'>Edit</a></td>";
			echo "<td><a href='edit.php?codMoto=" . $row->codMoto . "&delete=1'>Delete</a></td>";
			echo "</tr>";
		}
		echo "</table>";
	}
	else {
		echo "0 risultati";
	}
	
	$connect->close();
?>
</body>
</html>