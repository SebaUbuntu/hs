<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Crea Database Motociclismo</title>
</head>
<body>
<?php
	$host = "localhost";
	$user = "root";
	$password = "";

    //error_reporting(E_ALL ^ E_WARNING); // sopprime errori di warning
    //mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);  // crea exception per MySql invece che errori
    //mysqli_report(MYSQLI_REPORT_ERROR ^ MYSQLI_REPORT_STRICT); // crea errori per MySql invece che exception
    mysqli_report(MYSQLI_REPORT_OFF); // per PHP >= 8

    $connect = new mysqli($host, $user, $password);

	if ($connect->connect_errno) {
		exit("Impossibile connettersi all'host: " . $connect->connect_error);
	} 

    // se esiste il db esco dal programma
	if ($connect->select_db("motociclismo")) {  // se non abilitato MYSQLI_REPORT_OFF solleverebbe eccezione quindi try catch
        exit("Database esistente !");
    }

    /* try {
        $connect->select_db("motociclismo");
    }
    catch (Exception $errore) {
        echo($errore);
        exit("Database esistente !");
    }*/
    
    $comandoCreazioneDB = "CREATE DATABASE motociclismo "
						. "DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci";
        
    if($connect->query($comandoCreazioneDB) === false) {
        exit("errore nella creazione del database");
    }
    
    $connect->select_db("motociclismo");
        
    $comandoCreazioneTabellaMoto = "create table moto"
            . "(codMoto int NOT NULL"
            . ",marca varchar(20)"
            . ",cilindrata varchar(20)"
            . ",Primary Key(codMoto));";

	if($connect->query($comandoCreazioneTabellaMoto) === false) {
        exit("errore creazione tabella moto");
    }
       
    $comandoCreazioneTabellaPilota = "create table pilota"
            . "(codPilota int NOT NULL,"
            . "nome varchar(20),"
            . "moto int not null,"
            . "Primary Key(codPilota),"
            . "FOREIGN KEY collegamento_moto( moto ) "
            . "REFERENCES motociclismo.moto(codMoto));";

	if($connect->query($comandoCreazioneTabellaPilota) === false) {
		exit("errore  creazione  tabella pilota");
    }
        
    echo("Database Creato con successo !");
?>

</body>
</html>