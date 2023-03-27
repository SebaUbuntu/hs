import { API_URL, getLogoSquadra, defaultGiocatoriComparator, setTitoloPagina } from "./lib.js";

let infoSquadraElement = document.getElementById("info-squadra");
let listaGiocatoriElement = document.getElementById("lista-giocatori");

async function main() {
	let params = new URLSearchParams(window.location.search);

	if (!params.has('squadra_id')) {
		infoSquadraElement.innerHTML = "Errore: nessuna squadra specificata";
		return;
	}

	let squadra_id = params.get('squadra_id');

	let	response = await fetch(`${API_URL}/get_squadra.php?squadra_id=${squadra_id}`);
	if (!response.ok) {
		infoSquadraElement.innerHTML = `Errore ${response.status}: ${response.statusText}`;
		return;
	}

	let squadra = await response.json();

	// Imposta il titolo della pagina
	setTitoloPagina(squadra.nome);

	infoSquadraElement.innerHTML = `
		<div id="squadra-info">
			<h2>${squadra.nome}</h2>
			<p>Città: ${squadra.citta}</p>
			<p>Anno fondazione: ${squadra.anno_fondazione}</p>
		</div>

		<div id="squadra-logo">
			<img src="${getLogoSquadra(squadra.nome)}" alt="${squadra.nome}">
		</div>
	`

	response = await fetch(`${API_URL}/get_giocatori.php?squadra_id=${squadra_id}`);
	if (!response.ok) {
		listaGiocatoriElement.innerHTML = `Errore ${response.status}: ${response.statusText}`;
		return;
	}

	let giocatori = await response.json();

	// Ordina i giocatori
	giocatori.sort((a, b) => defaultGiocatoriComparator(a, b));

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
			</tr>
	`;

	for (let giocatore of giocatori) {
		html += `
			<tr>
				<td>
					<a href="visualizza_giocatore.html?giocatore_id=${giocatore.giocatore_id}">	
						${giocatore.cognome_nome}
					</a>
				</td>
				<td>${giocatore.data_nascita}</td>
				<td>${giocatore.posizione}</td>
				<td>${giocatore.crediti_iniziali}</td>
				<td>${giocatore.crediti_finali}</td>
				<td>${giocatore.nazionalita.join(", ")}</td>
			</tr>
		`;
	}

	html += "</table>";

	listaGiocatoriElement.innerHTML = html;
}

main();
