<?php
require_once "models/Squadra.php";

if (!isset($_GET['squadra_id'])) {
	die("No id provided");
}

$squadra_id = $_GET['squadra_id'];

$squadra = Squadra::from_id($squadra_id);

if ($squadra == null) {
	http_response_code(404);
	return;
}

print($squadra->to_json());
?>
