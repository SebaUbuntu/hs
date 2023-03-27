import { API_URL, getInfoUtente } from "./lib.js";

let mainContentElement = document.getElementById("main-content");

async function accettaInvito(fantalega_id) {
	window.location.href = `accetta_invito.html?fantalega_id=${fantalega_id}`;
}

async function rifiutaInvito(fantalega_id) {
	let response = await fetch(`${API_URL}/rifiuta_invito.php`, {
		method: "POST",
		body: JSON.stringify({
			fantalega_id,
		})
	});

	if (response.ok) {
		location.reload();
	} else {
		alert("Errore durante il rifiuto dell'invito");
	}
}

async function main() {
	// Aggiungi le funzioni per accettare/rifiutare inviti a livello globale
	window.accettaInvito = accettaInvito;
	window.rifiutaInvito = rifiutaInvito;

	let html = "";

	let utente = await getInfoUtente();
	if (utente == null) {
		html = `
			<h2>Non sei loggato</h2>
			<p><a href="login.html">Effettua il login</a></p>
		`;
	} else {
		html = `
			<h1>Ciao ${utente.nome}!</h1>
		`;

		// Ottieni la lista di inviti a partecipare a fantaleghe
		let inviti = [];
		let response = await fetch(`${API_URL}/get_inviti.php`);
		if (response.ok) {
			inviti = await response.json();
		}

		if (inviti.length > 0) {
			html += `
				<h2>Inviti</h2>
			`;
			for (let invito of inviti) {
				// Ottieni informazioni sulla fantalega
				response = await fetch(`${API_URL}/get_fantalega.php?fantalega_id=${invito.fantalega_id}`);
				if (!response.ok) {
					continue;
				}

				let fantalega = await response.json();

				// Ottieni informazioni sull'admin della fantalega
				response = await fetch(`${API_URL}/get_utente.php?utente_id=${fantalega.admin_id}`);
				if (!response.ok) {
					continue;
				}

				let admin = await response.json();

				html += `
					<div>
						<a href="visualizza_fantalega.html?fantalega_id=${invito.fantalega_id}">${fantalega.nome} (da ${admin.nome} ${admin.cognome})</a>
						<button onclick="accettaInvito(${invito.fantalega_id})">Accetta</button>
						<button onclick="rifiutaInvito(${invito.fantalega_id})">Rifiuta</button>
					</div>
				`;
			}
		}

		// Ottieni la lista di fantaleghe a cui l'utente partecipa
		html += `
			<h2>Le tue fantaleghe <a href="crea_fantalega.html">+ Crea</a></h2>
		`;
		let fantaleghe = [];
		response = await fetch(`${API_URL}/get_fantaleghe_utente.php`);
		if (response.ok) {
			fantaleghe = await response.json();
		}

		if (fantaleghe.length > 0) {
			for (let fantalega of fantaleghe) {
				html += `
					<div>
						<a href="visualizza_fantalega.html?fantalega_id=${fantalega.fantalega_id}">${fantalega.nome}</a>
					</div>
				`;
			}
		} else {
			html += `
				<p>Non partecipi a nessuna fantalega</p>
			`;
		}
	}

	mainContentElement.innerHTML = html;
}

main();
