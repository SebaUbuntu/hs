<?php
require_once "database/Database.php";

$connect = Database::get_connection();
if (!$connect) {
	exit("Errore connessione: $connect->connect_error");
}

$upload_ok = 1;

// Check file size
if ($_FILES["json"]["size"] > 500000) {
	print("File troppo grande");
	$upload_ok = 0;
}

// Check if $upload_ok is set to 0 by an error
if ($upload_ok == 0) {
	print("File non caricato");
	exit(1);
}

$json = json_decode(file_get_contents($_FILES["json"]["tmp_name"]), true);
if (!$json) {
	print("Errore durante la decodifica del file JSON");
	exit(1);
}

$squadre = array();

$sql = "SELECT * FROM squadre";
$result = $connect->query($sql);
while ($row = $result->fetch_assoc()) {
	$squadre[$row["nome"]] = $row["squadra_id"];
}

// Elimina tutti i giocatori
$sql = "DELETE FROM giocatori";
$result = $connect->query($sql);
$error = $connect->error;
if ($error) {
	print("Errore durante l'eliminazione dei giocatori: $error");
	exit(1);
}

// Aggiorna l'enum delle nazioni
$sql = 'ALTER TABLE nazionalita_giocatori MODIFY COLUMN nazione ENUM("'.implode('", "', $json["nazioni"]).'");';
$result = $connect->query($sql);
$error = $connect->error;
if ($error) {
	print("Errore durante l'aggiornamento delle nazioni: $error");
	exit(1);
}

// Prepara lo statement per l'inserimento dei giocatori
$sql = "INSERT INTO giocatori (cognome_nome, data_nascita, posizione, crediti_iniziali, crediti_finali, squadra_id) " .
	"VALUES (?, STR_TO_DATE(?, '%Y-%m-%d'), ?, ?, ?, ?)";
$statement = $connect->prepare($sql);
if (!$statement) {
	print("Errore durante la preparazione dello statement: $mysqli->error");
	exit(1);
}
$statement->bind_param("ssssii", $cognome_nome, $data_nascita, $posizione, $crediti_iniziali, $crediti_finali, $squadra_id);

// Prepara lo statement per l'inserimento delle nazionalità
$sql = "INSERT INTO nazionalita_giocatori (giocatore_id, nazione) VALUES (?, ?)";
$statement_nazionalita = $connect->prepare($sql);
if (!$statement_nazionalita) {
	print("Errore durante la preparazione dello statement: $mysqli->error");
	exit(1);
}
$statement_nazionalita->bind_param("is", $giocatore_id, $nazione);

// Aggiungi i giocatori
foreach ($json["giocatori"] as $giocatore) {
	$cognome_nome = $giocatore["cognome_nome"];
	$data_nascita = $giocatore["data_nascita"];
	$posizione = $giocatore["posizione"];
	$crediti_iniziali = $giocatore["crediti_iniziali"];
	$crediti_finali = $giocatore["crediti_finali"];
	$squadra = $giocatore["squadra"];
	$nazionalita = $giocatore["nazionalita"];

	// Squadra è una stringa, quindi deve essere convertita in id
	if (!array_key_exists($squadra, $squadre)) {
		print("Errore: squadra $squadra non trovata");
		exit(1);
	}

	$squadra_id = $squadre[$squadra];

	// Aggiungere il giocatore nella tabella giocatori
	$statement->execute();
	$error = $statement->error;
	if ($error) {
		print("Errore durante l'aggiunta di $cognome_nome: $error");
		exit(1);
	}

	// Aggiungere le nazionalità (array di string) nella tabella nazionalita_giocatori
	$giocatore_id = $statement->insert_id;
	for ($j = 0; $j < count($nazionalita); $j++) {
		$nazione = $nazionalita[$j];
		$statement_nazionalita->execute();
		$error = $statement_nazionalita->error;
		if ($error) {
			print("Errore durante l'aggiunta della nazionalità di $cognome_nome $nazione: $error");
			exit(1);
		}
	}
}
?>
