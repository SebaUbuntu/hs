<?php
require_once "models/giocatore.php";

if (!isset($_GET['giocatore_id'])) {
	http_response_code(400);
	return;
}

$giocatore_id = $_GET['giocatore_id'];

$giocatore = Giocatore::from_id($giocatore_id);
if (!$giocatore) {
	http_response_code(404);
	return;
}

print($giocatore->to_json());
?>
