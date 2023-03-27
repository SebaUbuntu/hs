<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Visualizzazione dati dal database con MySQLi</title>
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

	if($connect->select_db("motociclismo") == 0) {
		exit("Il database motociclismo non esiste");
	}
	
	// permette di evitare sql injection es iniettando nel parametro GET questo 'or'1'='1 
	$stmt = $connect->prepare("SELECT * FROM moto INNER JOIN pilota ON codmoto = moto where marca = ?");
	// elimina eventuali caratteri speciali tipo: NUL (ASCII 0), \n, \r, \, ', ", Ctrl-Z
	$marca = mysqli_real_escape_string($connect, $_GET["marca"]);
	$stmt->bind_param('s', $marca);
	$stmt->execute();
	$result = $stmt->get_result();
	$stmt->close();

	if ($result->num_rows > 0) {
		echo "<table><tr><th>cod Pilota</th><th>Nome</th><th>Marca</th></tr>";
		while($row = $result->fetch_assoc()) { // approccio ai risultati come array associativo
			echo "<tr><td>". $row["codPilota"]. "</td><td>". $row["nome"]. "</td><td>" . $row["marca"]. "</td></tr>";
		}
		echo "</table>";
	} 
	else {
		echo "0 risultati";
	}
	
	$numResult = $connect->query("SELECT count(*) as totaleMoto from moto ");
	$riga = $numResult->fetch_assoc();
	echo "<br/><br/>Sono presenti " . $riga['totaleMoto'] . " moto"; //totaleMoto;
	
	$connect->close();
?>
</body>
</html>