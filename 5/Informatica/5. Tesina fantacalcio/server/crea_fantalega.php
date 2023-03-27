<?php
require_once "models/fantalega.php";
require_once "models/fantasquadra.php";

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
	!isset($data['nome_fantalega']) ||
	!isset($data['nome_fantasquadra']) ||
    !isset($data['crediti_iniziali']) ||
    !isset($data['numero_portieri']) ||
    !isset($data['numero_difensori']) ||
    !isset($data['numero_centrocampisti']) ||
    !isset($data['numero_attaccanti'])
) {
	http_response_code(400);
	return;
}

$admin_id = $utente->utente_id;
$nome_fantalega = $data['nome_fantalega'];
$nome_fantasquadra = $data['nome_fantasquadra'];
$crediti_iniziali = $data['crediti_iniziali'];
$numero_portieri = $data['numero_portieri'];
$numero_difensori = $data['numero_difensori'];
$numero_centrocampisti = $data['numero_centrocampisti'];
$numero_attaccanti = $data['numero_attaccanti'];

$fantalega = Fantalega::crea_fantalega($nome_fantalega, $admin_id, $crediti_iniziali, $numero_portieri, $numero_difensori, $numero_centrocampisti, $numero_attaccanti);
if (!$fantalega) {
	http_response_code(400);
	return;
}

$fantasquadra = $fantalega->crea_fantasquadra($nome_fantasquadra, $admin_id);
if (!$fantasquadra) {
	http_response_code(500);
	return;
}

print($fantalega->to_json());
?>
