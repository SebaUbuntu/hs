<?php
require_once "database/database.php";

$conn = Database::get_connection();
if (!$conn) {
	return null;
}

$sql = "SELECT DISTINCT nazionalita_giocatori.nazione".
	   " FROM nazionalita_giocatori;";
$query = $conn->query($sql);
if (!$query) {
	return null;
}

$nazionalita = array();
while ($row = $query->fetch_assoc()) {
	$nazionalita[] = $row["nazione"];
}

$conn->close();

print(json_encode($nazionalita));
?>
