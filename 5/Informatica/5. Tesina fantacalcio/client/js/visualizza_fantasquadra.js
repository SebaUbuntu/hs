import { API_URL, getUtente, getSquadraIdToNome, setTitoloPagina } from "./lib.js";

let infoFantasquadraElement = document.getElementById("info-fantasquadra");
let listaGiocatoriElement = document.getElementById("lista-giocatori");

async function main() {
	let params = new URLSearchParams(window.location.search);

	if (!params.has('fantasquadra_id')) {
		infoFantasquadraElement.innerHTML = "Errore: nessuna fantasquadra specificata";
		return;
	}

	let fantasquadra_id = params.get('fantasquadra_id');

	let	response = await fetch(`${API_URL}/get_fantasquadra.php?fantasquadra_id=${fantasquadra_id}`);
	if (!response.ok) {
		infoFantasquadraElement.innerHTML = `Errore ${response.status}: ${response.statusText}`;
		return;
	}

	let fantasquadra = await response.json();

	// Imposta il titolo della pagina
	setTitoloPagina(fantasquadra.nome);

	// Ottieni informazioni sull'utente
	let utente = await getUtente(fantasquadra.utente_id);
	if (!utente) {
		infoFantasquadraElement.innerHTML = `Errore durante il caricamento dell'utente`;
		return;
	}

	infoFantasquadraElement.innerHTML = `
		<div id="fantasquadra-info">
			<h2>${fantasquadra.nome}</h2>
			<p>
				Utente:
				<a href="visualizza_utente.html?utente_id=${utente.utente_id}">
					${utente.nome} ${utente.cognome} (@${utente.username})
				</a>
			</p>
			<p>Crediti: ${fantasquadra.crediti}</p>
		</div>
	`

	response = await fetch(`${API_URL}/get_giocatori_fantasquadra.php?fantasquadra_id=${fantasquadra_id}`);
	if (!response.ok) {
		listaGiocatoriElement.innerHTML = `Errore ${response.status}: ${response.statusText}`;
		return;
	}

	let giocatori = await response.json();

	// Ordina i giocatori
	giocatori.sort((a, b) => defaultGiocatoriComparator(a, b));

	// Ottieni info sulle squadre
	let squadraIdToNome = await getSquadraIdToNome();

	let html = `
		<h2>Giocatori</h2>
		<table>
			<tr>
				<th>Nome</th>
				<th>Data di nascita</th>
				<th>Posizione</th>
				<th>Crediti iniziali</th>
				<th>Crediti finali</th>
				<th>Nazionalità</th>
				<th>Squadra</th>
			</tr>
	`;

	if (giocatori.length > 0) {
		for (let giocatore of giocatori) {
			html += `
				<tr>
					<td>${giocatore.cognome_nome}</td>
					<td>${giocatore.data_nascita}</td>
					<td>${giocatore.posizione}</td>
					<td>${giocatore.crediti_iniziali}</td>
					<td>${giocatore.crediti_finali}</td>
					<td>${giocatore.nazionalita.join(", ")}</td>
					<td>${squadraIdToNome[giocatore.squadra_id]}</td>
				</tr>
			`;
		}
	} else {
		html += `
			<tr>
				<td colspan="7">Nessun giocatore</td>
			</tr>
		`;
	}

	html += "</table>";

	listaGiocatoriElement.innerHTML = html;
}

main();
