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

		$sql = "UPDATE moto SET marca='".$_POST["marca"]."', cilindrata='".$_POST["cilindrata"]."' WHERE codMoto = ".$_POST["codMoto"];
		$result = $connect->query($sql);
		header("location: modifica.php");
		$connect->close();
	}
	else
		exit("Non si può invocare questa pagina senza parametri!");
?>
</body>
</html>