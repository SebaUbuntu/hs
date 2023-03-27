<?php
function delete_commessa($commessa_id) {
	require "database_info.php";

	$connect = new mysqli($host, $user, $password, $db);
	if ($connect->connect_error) {
		return;
	}

	$sql = "DELETE FROM tblCommesse WHERE idCommessa = $commessa_id;";

	$result = $connect->query($sql);
	$error = $connect->error;

	$connect->close();

	return array(
		"result" => $result,
		"error" => $error,
	);
}
?>
