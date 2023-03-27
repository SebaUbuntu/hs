<?php 
require_once "models/fantasquadra.php";
require_once "models/utente.php";

session_start();

if (!isset($_SESSION['utente_id'])) {
	http_response_code(401);
	return;
}

$utente_id = $_SESSION['utente_id'];
$utente = Utente::from_id($_SESSION['utente_id']);
if (!$utente) {
	http_response_code(401);
	return;
}

$data = json_decode(file_get_contents('php://input'), true);

if(
	!isset($data['fantasquadra_id']) ||
	!isset($data['giocatore_id'])
) {
	http_response_code(400);
	return;
}

$fantasquadra_id = $data['fantasquadra_id'];
$giocatore_id = $data['giocatore_id'];

$fantasquadra = Fantasquadra::from_id($fantasquadra_id);
if (!$fantasquadra) {
	http_response_code(400);
	return;
}

$fantalega = $fantasquadra->get_fantalega();
if (!$fantalega) {
	http_response_code(500);
	return;
}

// L'utente loggato deve essere l'admin della lega o il proprietario della squadra
if ($fantalega->admin_id != $utente_id && $fantasquadra->get_utente_id() != $utente_id) {
	http_response_code(403);
	return;
}

$result = $fantasquadra->svincola_giocatore($giocatore_id);
if (!$result) {
	http_response_code(500);
	return;
}
?>
