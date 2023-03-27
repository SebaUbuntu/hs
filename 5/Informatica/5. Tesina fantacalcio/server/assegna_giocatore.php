<?php 
include_once "models/fantasquadra.php";

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

if(
	!isset($data['fantasquadra_id']) ||
	!isset($data['giocatore_id']) ||
	!isset($data['crediti_spesi'])
) {
	http_response_code(400);
	return;
}

$fantasquadra_id = $data['fantasquadra_id'];
$giocatore_id = $data['giocatore_id'];
$crediti_spesi = $data['crediti_spesi'];

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
if ($fantalega->admin_id != $utente->utente_id && $fantasquadra->get_utente_id() != $utente->utente_id) {
	http_response_code(403);
	return;
}

$result = $fantasquadra->assegna_giocatore($giocatore_id, $crediti_spesi);
if (!$result) {
	http_response_code(500);
	return;
}
?>
