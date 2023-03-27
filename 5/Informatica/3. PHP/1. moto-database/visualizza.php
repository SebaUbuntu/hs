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

	// Connessione con incluso DB
	$connect = new mysqli($host, $user, $password, $db);
	if ($connect->connect_errno) {
		exit("Errore connessione: " . $connect->connect_error);
	} 

	$sql = "SELECT * FROM moto INNER JOIN pilota ON codmoto = moto where marca = '".$_GET["marca"]."'";

	$result = $connect->query($sql);

	if ($result->num_rows > 0) {
		echo "<table><tr><th>cod Pilota</th><th>Nome</th><th>Marca</th></tr>";
		while($row = $result->fetch_assoc()) { // approccio ai risultati come array associativo
			echo "<tr><td>". $row["codPilota"]. "</td><td>". $row["nome"]. "</td><td>" . $row["marca"]. "</td></tr>";
		}
		echo "</table>";
	} 
	else {
		echo "nessuna moto ".$_GET["marca"]." trovata!";
	}
	
	$numResult = $connect->query("SELECT count(*) as totaleMoto from moto ");
	$riga = $numResult->fetch_assoc();
	echo "<br/><br/>Sono presenti " . $riga['totaleMoto'] . " moto in totale"; //totaleMoto;
	// 2 alternative di lettura di $numResult
	
	//$riga = $numResult->fetch_row();
	//echo "<br/><br/>Sono presenti " . $riga[0] . " moto"; //totaleMoto;

	//$riga = $numResult->fetch_object();
	//echo "<br/><br/>Sono presenti " . $riga->totaleMoto . " moto"; //totaleMoto;
	$connect->close();
?>
</body>
</html>