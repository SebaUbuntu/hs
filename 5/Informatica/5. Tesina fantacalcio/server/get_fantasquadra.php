<?php
require_once "models/fantasquadra.php";

if (!isset($_GET['fantasquadra_id'])) {
	http_response_code(400);
	return;
}

$fantasquadra_id = $_GET['fantasquadra_id'];

$fantasquadra = Fantasquadra::from_id($fantasquadra_id);
if ($fantasquadra == null) {
	http_response_code(404);
	return;
}

print($fantasquadra->to_json());
?>
