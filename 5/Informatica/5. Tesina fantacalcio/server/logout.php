<?php
require_once "models/utente.php";

session_start();

if (!isset($_SESSION['utente_id'])) {
	http_response_code(401);
	return;
}

session_destroy();
?>
