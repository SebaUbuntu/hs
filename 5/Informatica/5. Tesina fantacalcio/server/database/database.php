<?php
class Database {
	private static $host = "localhost";
	private static $user = "fantacalcio";
	private static $password = "fantacalcio";
	private static $database = "fantacalcio";

	static function get_connection() {
		$conn = new mysqli(Database::$host, Database::$user, Database::$password, Database::$database);

		if ($conn->connect_error) {
			return null;
		}

		return $conn;
	}
}
?>
