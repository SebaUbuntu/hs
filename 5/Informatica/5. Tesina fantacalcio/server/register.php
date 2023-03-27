<?php
require_once "models/utente.php";

session_start();

$data = json_decode(file_get_contents('php://input'), true);

if (
	!isset($data['username']) ||
	!isset($data['nome']) ||
	!isset($data['cognome']) ||
	!isset($data['email']) ||
	!isset($data['password'])
) {
	http_response_code(400);
	return;
}

$username = $data['username'];
$password = $data['password'];
$nome = $data['nome'];
$cognome = $data['cognome'];
$email = $data['email'];

$utente = Utente::register($username, $password, $nome, $cognome, $email);
if (!$utente) {
	http_response_code(400);
	return;
}

$_SESSION['utente_id'] = $utente->utente_id;

print($utente->to_json());
?>
