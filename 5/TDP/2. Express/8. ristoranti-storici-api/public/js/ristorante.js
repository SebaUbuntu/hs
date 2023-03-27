const infoRistoranteElement = document.getElementById("info-ristorante");
const recensioneFormElement = document.getElementById("recensione-form");
const recensioniElement = document.getElementById("recensioni");

const params = new URLSearchParams(window.location.search);

async function aggiornaRecensioni() {
	if (!params.has("ristorante_id")) {
		console.log("Errore: ID del ristorante non specificato");
		recensioniElement.innerHTML = "<p>Errore durante il caricamento delle recensioni</p>";
		return;
	}

	let ristoranteId = params.get("ristorante_id");

	response = await fetch(`api/ristoranti/${ristoranteId}/recensioni`);
	if (!response.ok) {
		console.error(`Errore durante il caricamento delle recensioni del ristorante ${ristoranteId}: ${response.status}`);
		recensioniElement.innerHTML = "<p>Errore durante il caricamento delle recensioni</p>";
		return;
	}

	let recensioni = await response.json();

	if (recensioni.length === 0) {
		recensioniElement.innerHTML = "<p>Nessuna recensione trovata</p>";
		return;
	}

	recensioniElement.innerHTML = "";

	recensioni.sort((a,b) => b.data - a.data);

	recensioni.forEach(recensione => {
		recensioniElement.innerHTML += `
			<div class="recensione">
				<div class="recensione-info">
					<p class="recensione-stelle">Stelle: ${[...Array(recensione.stelle).keys()].map(() => "★").join("")}</p>
					<p class="recensione-data">Data: ${new Date(recensione.data * 1000).toLocaleDateString()}</p>
				</div>
				<p class="recensione-testo">${recensione.commento}</p>
			</div>
		`
	});
}

async function main() {
	if (!params.has("ristorante_id")) {
		console.log("Errore: ID del ristorante non specificato");
		infoRistoranteElement.innerHTML = "<p>Errore: Ristorante non trovato</p>";
		return;
	}

	let ristoranteId = params.get("ristorante_id");

	let response = await fetch(`api/ristoranti/${ristoranteId}`);
	if (!response.ok) {
		console.error(`Errore durante il caricamento del ristorante ${ristoranteId}: ${response.status}`);
		infoRistoranteElement.innerHTML = "<p>Errore: Ristorante non trovato</p>";
		return;
	}

	let ristorante = await response.json();

	// Ottieni informazioni sulla provincia
	response = await fetch(`api/province/${ristorante.provincia}`);
	if (!response.ok) {
		console.error(`Errore durante il caricamento della provincia ${ristorante.provincia}: ${response.status}`);
		infoRistoranteElement.innerHTML = "<p>Errore: Ristorante non trovato</p>";
		return;
	}

	let provincia = await response.json();

	// Ottieni informazioni sui piatti tipici
	let piattiTipici = {};
	const TIPOLOGIE = [
		"Primi",
		"Secondi",
		"Dessert",
		"Bevande",
	]
	
	for (let tipologia of TIPOLOGIE) {
		response = await fetch(`api/regioni/${provincia.regione}/piatti/${tipologia}`);
		if (!response.ok) {
			console.error(`Errore durante il caricamento dei piatti tipici per la regione ${provincia.regione}: ${response.status}`);
			infoRistoranteElement.innerHTML = "<p>Errore: Ristorante non trovato</p>";
			return;
		}

		piattiTipici[tipologia] = await response.json();
	}

	// Ottieni informazioni sul meteo della provincia
	response = await fetch(`api/province/${ristorante.provincia}/meteo`);
	if (!response.ok) {
		console.error(`Errore durante il caricamento del meteo per la provincia ${ristorante.provincia}: ${response.status}`);
		infoRistoranteElement.innerHTML = "<p>Errore: Ristorante non trovato</p>";
		return;
	}

	let meteo = await response.json();

	infoRistoranteElement.innerHTML = `
		<h1 class="ristorante-nome">${ristorante.nome}</h1>
		<p class="ristorante-regione">Regione: ${provincia.regione}</p>
		<p class="ristorante-provincia">Provincia: ${provincia.nome} (${provincia.sigla})</p>
		<p class="ristorante-meteo">Meteo provincia: ${meteo.weather[0].description}</p>
		<p class="ristorante-indirizzo">Indirizzo: ${ristorante.indirizzo}</p>
		<p class="ristorante-anno-apertura">Anno di apertura: ${ristorante.anno_apertura}</p>
		<p class="ristorante-specialita">Specialità: ${ristorante.specialita}</p>
		<p class="ristorante-piatti-tipici">
			Piatti tipici della regione:
			<ul>
				${TIPOLOGIE.map(tipologia => `
					<li>
						${tipologia}:
						<ul>
							${piattiTipici[tipologia].map(piatto => `
								<li>${piatto.nome}</li>
							`).join("")}
						</ul>
					</li>
				`).join("")}
			</ul>
		</p>
	`

	recensioneFormElement.addEventListener("submit", async event => {
		event.preventDefault();

		let stelle = document.getElementById("stelle").value;
		let commento = document.getElementById("commento").value;

		let recensione = {
			"stelle": stelle,
			"commento": commento,
		}

		response = await fetch(`api/ristoranti/${ristoranteId}/recensioni`, {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(recensione),
		});

		if (!response.ok) {
			console.error(`Errore durante l'inserimento della recensione: ${response.status}`);
			return;
		}

		await aggiornaRecensioni();
	});

	await aggiornaRecensioni();
}

main();
