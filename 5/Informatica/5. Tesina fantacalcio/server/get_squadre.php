<?php
require_once "models/Squadra.php";

$squadre = Squadra::get_all();

if ($squadre == null) {
	http_response_code(404);
	return;
}

print(json_encode($squadre));
?>
