<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Inserimento dati nel database</title>
</head>
<body>

<?php
	$host = "localhost";
	$user = "root";
	$password = "";

	$connect = mysqli_connect($host, $user, $password);
	if ($connect->connect_errno) {
		exit("Impossibile connettersi all' host" . $connect->connect_error);
	} 

	if($connect->select_db("motociclismo") === false) {
		exit("Il database non esiste");
	}

	$inserisciMoto1 = "insert into moto values(1, 'yamaha', 800)";

	if($connect->query($inserisciMoto1) === false)	{
		exit("errore inserimento moto 1");
	}

	$inserisciMoto2 = "insert into moto values(2, 'honda', 800)";

	if($connect->query($inserisciMoto2) === false) {
        exit("errore inserimento moto 2");
	}

	$inserisciPilota1 = "insert into pilota values(1, 'rossi', 1)";
	if($connect->query($inserisciPilota1) === false) {
        exit("errore inserimento pilota 1");
	}

	$inserisciPilota2 = "insert into pilota values(2, 'lorenzo', 1)";
	if($connect->query($inserisciPilota2) === false) {
        exit("errore inserimento pilota");
	}

	$inserisciPilota3 = "insert into pilota values(3, 'marquez', 2)";
	if($connect->query($inserisciPilota3) === false) {
        exit("errore inserimento pilota");
	}

	$inserisciPilota4 = "insert into pilota values(4, 'pedrosa', 2)";
	if($connect->query($inserisciPilota4) === false) {
        exit("errore inserimento pilota");
	}

	echo("database popolato correttamente");
?>    
</body>
</html>