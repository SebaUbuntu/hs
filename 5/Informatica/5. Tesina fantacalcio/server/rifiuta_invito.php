<?php 
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
	!isset($data['fantalega_id'])
) {
	http_response_code(400);
	return;
}

$fantalega_id = $data['fantalega_id'];

$invito = Invito::elimina_invito($utente->utente_id, $fantalega_id);
if (!$invito) {
	http_response_code(500);
	return;
	http_response_code(200);
}
?>
