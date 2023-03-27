import { API_URL, getLogoSquadra } from "./lib.js";

let listaSquadreElement = document.getElementById("lista-squadre");

async function main() {
	let	response = await fetch(`${API_URL}/get_squadre.php`);
	if (!response.ok) {
		listaSquadreElement.innerHTML = `Errore ${response.status}: ${response.statusText}`;
		return;
	}

	let squadre = await response.json();
	squadre.sort((a, b) => a.nome.localeCompare(b.nome));

	let html = `
		<h2>Squadre Serie A TIM</h2>

		<div class="grid-container">
	`;

	for (let squadra of squadre) {
		html += `
			<a class="grid-item" href="visualizza_squadra.html?squadra_id=${squadra.squadra_id}">	
				<img src="${getLogoSquadra(squadra.nome)}" alt="${squadra.nome}">
				<p>${squadra.nome}</p>
			</a>
		`;
	}

	html += `
		</div>
	`;

	listaSquadreElement.innerHTML = html;
}

main();
