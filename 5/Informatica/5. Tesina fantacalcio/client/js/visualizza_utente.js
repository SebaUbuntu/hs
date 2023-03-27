import { getUtente, setTitoloPagina } from "./lib.js";

let infoUtenteElement = document.getElementById("info-utente");

async function main() {
	let params = new URLSearchParams(window.location.search);

	if (!params.has('utente_id')) {
		infoSquadraElement.innerHTML = "Errore: nessun utente specificato";
		return;
	}

	let utenteId = params.get('utente_id');

	let utente = await getUtente(utenteId);

	// Imposta il titolo della pagina
	setTitoloPagina(`${utente.nome} ${utente.cognome}`);

	infoUtenteElement.innerHTML = `
		<div id="utente-info">
			<h2>${utente.nome} ${utente.cognome}</h2>
			<p>Username: ${utente.username}</p>
			<p>Email: ${utente.email}</p>
		</div>
	`
}

main();
