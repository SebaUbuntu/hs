<?php
require_once "models/utente.php";

session_start();

if (!isset($_SESSION['utente_id'])) {
	http_response_code(401);
	return;
}

$utente = Utente::from_id($_SESSION['utente_id']);
if (!$utente) {
	http_response_code(401);
	return;
}

$inviti = $utente->get_inviti_attivi();

print(json_encode($inviti));
?>
