<?php
require "add_commessa.php";
require "delete_commessa.php";
require "get_commessa.php";
require "get_commesse.php";
require "get_commesse_per_tipo_intervento.php";
require "get_reparti.php";
require "get_tipi_intervento.php";
require "get_utenti.php";
require "update_commessa.php";

$result = array(
	"error" => "Richiesta non processata",
);

if ($_SERVER['REQUEST_METHOD'] == "GET") {
	if (!isset($_GET["get"])) {
		http_response_code(400);
		exit();
	}

	$get = $_GET["get"];

	if ($get == "get_commessa") {
		if (!isset($_GET["id_commessa"])) {
			http_response_code(400);
			exit();
		}

		$result = get_commessa($_GET["id_commessa"]);
	} else if ($get == "get_commesse") {
		$result = get_commesse();
	} else if ($get == "get_commesse_per_tipo_intervento") {
		$result = get_commesse_per_tipo_intervento();
	} else if ($get == "get_reparti") {
		$result = get_reparti();
	} else if ($get == "get_tipi_intervento") {
		$result = get_tipi_intervento();
	} else if ($get == "get_utenti") {
		$result = get_utenti();
	} else {
		http_response_code(404);
		exit();
	}
} else if ($_SERVER["REQUEST_METHOD"] == "POST") {
	if (!isset($_POST["get"])) {
		http_response_code(400);
		exit();
	}

	$get = $_POST["get"];

	if ($get == "add_commessa") {
		if (
			!isset($_POST["data_commessa"]) ||
			!isset($_POST["descrizione"]) ||
			!isset($_POST["id_richiedente"]) ||
			!isset($_POST["id_reparto"]) ||
			!isset($_POST["id_tipo_intervento"])
		) {
			http_response_code(400);
			exit();
		}

		$result = add_commessa(
			$_POST["data_commessa"],
			$_POST["descrizione"],
			$_POST["id_richiedente"],
			$_POST["id_reparto"],
			$_POST["id_tipo_intervento"]
		);
	} else if ($get == "delete_commessa") {
		if (!isset($_POST["id_commessa"])) {
			http_response_code(400);
			exit();
		}

		$result = delete_commessa($_POST["id_commessa"]);
	} else if ($get == "update_commessa") {
		if (
			!isset($_POST["id_commessa"]) ||
			!isset($_POST["data_commessa"]) ||
			!isset($_POST["descrizione"]) ||
			!isset($_POST["id_richiedente"]) ||
			!isset($_POST["id_reparto"]) ||
			!isset($_POST["id_tipo_intervento"])
		) {
			http_response_code(400);
			exit();
		}

		$result = update_commessa(
			$_POST["id_commessa"],
			$_POST["data_commessa"],
			$_POST["descrizione"],
			$_POST["id_richiedente"],
			$_POST["id_reparto"],
			$_POST["id_tipo_intervento"],
		);
	} else {
		http_response_code(404);
		exit();
	}
} else {
	http_response_code(405);
	exit();
}

print(json_encode($result));
?>
