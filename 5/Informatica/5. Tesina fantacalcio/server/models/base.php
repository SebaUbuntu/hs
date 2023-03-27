<?php
/**
 * Classe astratta per tutti i modelli
 */
abstract class Base {
    /**
     * Restituisce una rappresentazione JSON dell'oggetto
     * @return string una stringa JSON
     */
	public function to_json() {
		return json_encode($this);
	}
}
?>
