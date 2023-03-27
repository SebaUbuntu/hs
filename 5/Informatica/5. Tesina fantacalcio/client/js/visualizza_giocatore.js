import { API_URL, getSquadraIdToNome, setTitoloPagina } from "./lib.js";

let infoGiocatoreElement = document.getElementById("info-giocatore");
let graficoCreditiElement = document.getElementById("grafico-crediti");

async function main() {
	let params = new URLSearchParams(window.location.search);

	if (!params.has('giocatore_id')) {
		infoFantasquadraElement.innerHTML = "Errore: nessuna giocatore specificato";
		return;
	}

	let giocatore_id = params.get('giocatore_id');

	let response = await fetch(`${API_URL}/get_giocatore.php?giocatore_id=${giocatore_id}`);
	if (!response.ok) {
		infoGiocatoreElement.innerHTML = `Errore ${response.status}: ${response.statusText}`;
		return;
	}

	let giocatore = await response.json();

	// Imposta il titolo della pagina
	setTitoloPagina(giocatore.cognome_nome);

	let squadraIdToNome = await getSquadraIdToNome();

	infoGiocatoreElement.innerHTML = `
		<div id="giocatore-info">
			<h2>${giocatore.cognome_nome}</h2>
			<p>Data di nascita: ${giocatore.data_nascita}</p>
			<p>
				Squadra:
				<a href="visualizza_squadra.html?squadra_id=${giocatore.squadra_id}">
					${squadraIdToNome[giocatore.squadra_id]}
				</a>
			</p>
			<p>Posizione: ${giocatore.posizione}</p>
			<p>Crediti iniziali: ${giocatore.crediti_iniziali}</p>
			<p>Crediti finali: ${giocatore.crediti_finali} ${
				giocatore.crediti_finali > giocatore.crediti_iniziali ? "↑" :
					giocatore.crediti_finali < giocatore.crediti_iniziali ? "↓" : "-"
			}</p>
			<p>Nazionalità: ${giocatore.nazionalita.join(", ")}</p>
		</div>
	`;

	// Grafico crediti
	let maxCrediti = Math.max(giocatore.crediti_iniziali, giocatore.crediti_finali);

	new Chart(graficoCreditiElement, {
		type: 'line',
		data: {
			labels: ['Crediti iniziali', 'Crediti finali'],
			datasets: [
				{
					label: 'Crediti',
					fill: false,
					lineTension: 0,
					backgroundColor: "rgba(0,0,255,1.0)",
					borderColor: "rgba(0,0,255,0.1)",
					data: [giocatore.crediti_iniziali, giocatore.crediti_finali],
				}
			]
		},
		options: {
			legend: { display: false },
			scales: {
				y: {
					beginAtZero: true,
					// Aggiunge il 10% di spazio sopra il massimo valore
					max: Math.ceil(maxCrediti + (maxCrediti * 0.1)),
					// No decimali
					ticks: { precision: 0 },
				}
			},
		}
	});
}

main();
