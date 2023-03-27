import { API_URL, getSquadraIdToNome } from "./lib.js";

let posizioneElement = document.getElementById("posizione");
let nazionalitaElement = document.getElementById("nazionalita");
let cognomeNomeElement = document.getElementById("cognome-nome");
let cercaElement = document.getElementById("cerca");
let listaGiocatoriElement = document.getElementById("lista-giocatori");

async function updateListaGiocatori() {
	// Ottieni la lista di giocatori
	let url = `${API_URL}/get_giocatori.php`;
	let params = {};
	if (posizioneElement.value != "Tutte") {
		params.posizione = posizioneElement.value;
	}
	if (nazionalitaElement.value != "Tutte") {
		params.nazionalita = nazionalitaElement.value;
	}
	if (cognomeNomeElement.value != "") {
		params.cognome_nome = cognomeNomeElement.value;
	}
	if (Object.keys(params).length > 0) {
		url += "?" + new URLSearchParams(params);
	}

    let response = await fetch(url);
	if (!response.ok) {
		listaGiocatoriElement.innerHTML = `Errore ${response.status}: ${response.statusText}`;
		return;
	}

    let giocatori = await response.json();

	// Ordina i giocatori
	giocatori.sort((a, b) => {
		// Ordina per nome
		return a.cognome_nome.localeCompare(b.cognome_nome);
	});

	// Ottieni info sulle squadre
	let squadraIdToNome = await getSquadraIdToNome();

    let html = `
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
					<td>
						<a href="visualizza_squadra.html?squadra_id=${giocatore.squadra_id}">
							${squadraIdToNome[giocatore.squadra_id]}
						</a>
					</td>
				</tr>
			`;
		}
	} else {
		html += `
			<tr>
				<td colspan="7">Nessun giocatore trovato</td>
			</tr>
		`;
	}

    html += "</table>";

    listaGiocatoriElement.innerHTML = html;
}

async function main() {
	// Ottieni la lista di nazionalità
	let	response = await fetch(`${API_URL}/get_nazionalita.php`);
	if (!response.ok) {
		listaGiocatoriElement.innerHTML = `Errore ${response.status}: ${response.statusText}`;
		return;
	}

	let nazionalita = await response.json();

	// Ordina le nazionalità
	nazionalita.sort((a, b) => {
		// Ordina per nome
		return a.localeCompare(b);
	});

	// Aggiungi le opzioni al menu a tendina
	for (let nazione of nazionalita) {
		let optionElement = document.createElement("option");
		optionElement.value = nazione;
		optionElement.textContent = nazione;
		nazionalitaElement.appendChild(optionElement);
	}

	// Quando viene premuto invio, aggiorna la lista di giocatori
	cognomeNomeElement.addEventListener("keyup", (event) => {
		if (event.key == "Enter") {
			updateListaGiocatori();
		}
	});

	// Aggiungi il listener per il bottone di ricerca
	cercaElement.addEventListener("click", updateListaGiocatori);

	await updateListaGiocatori();
}

main();
