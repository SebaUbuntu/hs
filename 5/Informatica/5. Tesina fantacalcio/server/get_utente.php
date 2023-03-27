<?php
require_once "models/utente.php";

session_start();

if (
	!isset($_GET["utente_id"]) &&
	!isset($_GET["username_or_email"])
) {
	http_response_code(400);
	return;
}

if (isset($_GET["utente_id"])) {
	$utente = Utente::from_id($_GET["utente_id"]);
} else if (isset($_GET["username_or_email"])) {
	$utente = Utente::from_username_or_email($_GET["username_or_email"]);
}

if (!$utente) {
	http_response_code(404);
	return;
}

print($utente->to_json());
?>
