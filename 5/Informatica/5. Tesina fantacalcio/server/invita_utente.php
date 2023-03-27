<?php
require_once "models/fantalega.php";
require_once "models/invito.php";
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

$data = json_decode(file_get_contents('php://input'), true);

if (
	!isset($data['fantalega_id']) ||
	!isset($data['username_or_email'])
) {
	http_response_code(400);
	return;
}

$fantalega_id = $data['fantalega_id'];
$username_or_email = $data['username_or_email'];

$fantalega = Fantalega::from_id($fantalega_id);
if (!$fantalega) {
	http_response_code(404);
	return;
}

// L'utente loggato deve essere admin della fantalega
if ($fantalega->admin_id != $utente->utente_id) {
	http_response_code(401);
	return;
}

$utente_invitato = Utente::from_username_or_email($username_or_email);
if (!$utente_invitato) {
	http_response_code(404);
	return;
}

// L'utente invitato non può essere l'utente loggato
if ($utente_invitato->utente_id == $utente->utente_id) {
	http_response_code(400);
	return;
}

$invito = Invito::crea_invito($utente_invitato->utente_id, $fantalega->fantalega_id);
if (!$invito) {
	http_response_code(500);
	return;
}

print($invito->to_json());
?>
