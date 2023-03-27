import { API_URL, getInfoUtente, getUtente, setTitoloPagina } from "./lib.js";

let infoFantalegaElement = document.getElementById("info-fantalega");
let listaFantasquadreElement = document.getElementById("lista-fantasquadre");

async function main() {
	let utente = await getInfoUtente();

	let params = new URLSearchParams(window.location.search);

	if (!params.has('fantalega_id')) {
		infoFantalegaElement.innerHTML = "ID fantalega non specificato";
		return;
	}

	let fantalega_id = params.get('fantalega_id');

	let response = await fetch(`${API_URL}/get_fantalega.php?fantalega_id=${fantalega_id}`);
	if (!response.ok) {
		infoFantalegaElement.innerText = "Questa fantalega non esiste";
	}

	let fantalega = await response.json();

	// Imposta il titolo della pagina
	setTitoloPagina(fantalega.nome);

	// Ottieni informazioni sull'admin della fantalega
	let admin = await getUtente(fantalega.admin_id);
	if (!admin) {
		infoFantalegaElement.innerText = "Errore durante il caricamento dell'admin della fantalega";
		return;
	}

	infoFantalegaElement.innerHTML = `
		<h2>${fantalega.nome}</h2>
		<p>Admin: <a href="visualizza_utente.html?utente_id=${admin.utente_id}">${admin.nome} ${admin.cognome} (@${admin.username})</a></p>
        <p>Crediti iniziali: ${fantalega.crediti_iniziali}</p>
        <p>Numero portieri: ${fantalega.numero_portieri}</p>
        <p>Numero difensori: ${fantalega.numero_difensori}</p>
        <p>Numero centrocampisti: ${fantalega.numero_centrocampisti}</p>
        <p>Numero attaccanti: ${fantalega.numero_attaccanti}</p>
        <h3 id="asta-button"><a href="asta.html?fantalega_id=${fantalega.fantalega_id}">Inizia asta</a></h3>
	`

	// Ottieni la lista di squadre
	let fantasquadre = [];
	response = await fetch(`${API_URL}/get_fantasquadre.php?fantalega_id=${fantalega_id}`);
	if (response.ok) {
		fantasquadre = await response.json();
	}

	let html = `
		<h2>Fantasquadre ${
			utente && utente.utente_id == fantalega.admin_id ? `
				<a href="invita_utente.html?fantalega_id=${fantalega_id}">Invita</a>
			` : ``
		}
		</h2>
		<table>
			<tr>
				<th>Nome</th>
				<th>Utente</th>
			</tr>
	`;
	if (fantasquadre.length > 0) {
		for (let fantasquadra of fantasquadre) {
			let utente_fantasquadra = await getUtente(fantasquadra.utente_id);

			html += `
				<tr>
					<td>
						<a href="visualizza_fantasquadra.html?fantasquadra_id=${fantasquadra.fantasquadra_id}">
							${fantasquadra.nome}
						</a>
					</td>
					<td>
						<a href="visualizza_utente.html?utente_id=${utente_fantasquadra.utente_id}">
							${utente_fantasquadra.nome} ${utente_fantasquadra.cognome} (@${utente_fantasquadra.username})
						</a>
					</td>
				</tr>
			`;
		}
	} else {
		html += `
			<tr>
				<td colspan="2">Nessuna squadra</td>
			</tr>
		`;
	}
	html += "</table>";

	listaFantasquadreElement.innerHTML = html;
}

main();
