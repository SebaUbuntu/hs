<?php
require_once "models/fantalega.php";

if (!isset($_GET['fantalega_id'])) {
	http_response_code(400);
	return;
}

$fantalega_id = $_GET['fantalega_id'];

$fantalega = Fantalega::from_id($fantalega_id);
if ($fantalega == null) {
	http_response_code(404);
	return;
}

print(json_encode($fantalega->get_fantasquadre()));
?>
