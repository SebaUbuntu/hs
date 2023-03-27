<html>
<head>
	<title>Modifica Moto</title>

	<script type="text/javascript">
		function controlla() {
			if(document.getElementById("marca").value==="") {
				alert("Inserire una marca");
				return false;
			}
		}
	</script>
</head>

<body>

 <?php
	if (isset($_GET["codMoto"])) {
		$host = "localhost";
		$user = "root";
		$password = "";
		$db = "motociclismo";
		$connect = new mysqli($host, $user, $password, $db);
		if ($connect->connect_error)
			exit("Errore connessione: " . $connect->connect_error);

		if (isset($_GET["delete"]) && $_GET["delete"] == "1") {
			$connect->query("DELETE FROM pilota WHERE moto = ".$_GET["codMoto"]);
			$connect->query("DELETE FROM moto WHERE codMoto = ".$_GET["codMoto"]);
			header("Location: modifica.php")
			exit();
		}

		$result = $connect->query("SELECT * FROM moto WHERE codMoto = ".$_GET["codMoto"]);
		$moto = $result->fetch_assoc();
	}
	else
		exit("Non si può invocare questa pagina senza parametri!");
 ?>
  
	<p>Fai le modifiche e premi Salva</p>
	<form id="formEdit" name="formEdit" method="post" action="update.php">
		<input type="hidden" name="codMoto" value='<?php echo $moto["codMoto"]; ?>'/>
		Marca<input type="text" name="marca" value='<?php echo $moto["marca"]; ?>'/>
		Cilindrata<input type="text" name="cilindrata" id="cilindrata" value='<?php echo $moto["cilindrata"]; ?>'/>
		<input type="submit" name="salva" id="salva" value="Salva" onclick="return controlla()" />
	</form>
</body>
</html>