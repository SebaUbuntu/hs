<!DOCTYPE html>
<html>
<head>
	<title>Update</title>
</head>

<body>
<?php
	if ($_SERVER['REQUEST_METHOD'] === 'POST') {
		$host = "localhost";
		$user = "root";
		$password = "";
		$db = "motociclismo";
		$connect = new mysqli($host, $user, $password, $db);
		if ($connect->connect_error)
			exit("Errore connessione: " . $connect->connect_error);

        $sql = "INSERT INTO moto (codMoto, marca, cilindrata) VALUES ('".$_POST["codMoto"]."','".$_POST["marca"]."','".$_POST["cilindrata"]."')";
		$result = $connect->query($sql);

		header("location: modifica.php");
		$connect->close();
	}
	else
		exit("Non si può invocare questa pagina senza parametri!");
?>
</body>
</html>