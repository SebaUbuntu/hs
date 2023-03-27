<?php
require_once "models/fantasquadra.php";

if (
    !isset($_GET['fantasquadra_id']) ||
    !isset($_GET['giocatore_id'])
) {
    http_response_code(400);
    return;
}

$fantasquadra_id = $_GET['fantasquadra_id'];
$giocatore_id = $_GET['giocatore_id'];

$fantasquadra = Fantasquadra::from_id($fantasquadra_id);
if ($fantasquadra == null) {
    http_response_code(404);
    return;
}

$giocatore = Giocatore::from_id($giocatore_id);
if ($giocatore == null) {
    http_response_code(404);
    return;
}

$crediti_spesi = $fantasquadra->get_crediti_spesi_per_giocatore($giocatore->giocatore_id);
if (is_null($crediti_spesi)) {
    http_response_code(404);
    return;
}

$json = [
    "crediti_spesi" => $crediti_spesi
];

print(json_encode($json));
?>
