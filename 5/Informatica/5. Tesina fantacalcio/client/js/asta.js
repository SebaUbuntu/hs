import { API_URL, getInfoUtente, defaultGiocatoriComparator } from "./lib.js";

let mainContentElement = document.getElementById("main-content");
let astaMainElement = document.getElementById("asta-main");
let infoFantasquadreElement = document.getElementById("info-fantasquadre");

/**
 * Classe che gestisce l'asta.
 */
class Asta {
	/**
	 * Costruttore.
	 * @param {number} fantalegaId ID della fantalega
	 * @param {number} creditiIniziali Crediti iniziali per ogni squadra
	 * @param {number} numeroPortieri Numero di portieri per ogni squadra
	 * @param {number} numeroDifensori Numero di difensori per ogni squadra
	 * @param {number} numeroCentrocampisti Numero di centrocampisti per ogni squadra
	 * @param {number} numeroAttaccanti Numero di attaccanti per ogni squadra
	 */
	constructor(
		fantalegaId,
		creditiIniziali,
		numeroPortieri,
		numeroDifensori,
		numeroCentrocampisti,
		numeroAttaccanti,
	) {
		this.fantalegaId = fantalegaId;
		this.creditiIniziali = creditiIniziali;
		this.numeroPortieri = numeroPortieri;
		this.numeroDifensori = numeroDifensori;
		this.numeroCentrocampisti = numeroCentrocampisti;
		this.numeroAttaccanti = numeroAttaccanti;

		this.giocatoreInAsta = null;
	}

	/**
	 * Avvia l'asta.
	 * @returns {Promise<void>}
	 */
	async start() {
		// Aggiorna le informazioni sulle fantasquadre
		await this.updateInfoFantasquadre();

		// Avvia l'asta
		await this.apriSelettoreGiocatore();
	}

	/**
	 * Apre il selettore dei giocatori in asta-main.
	 * Deve essere una casella di testo con menu a tendina man mano che si scrive il nome del giocatore.
	 */
	async apriSelettoreGiocatore() {
		// Resetta il giocatore in asta
		this.giocatoreInAsta = null;

		// Mostra il selettore dei giocatori
		astaMainElement.innerHTML = `
			<h2>Seleziona un giocatore</h2>
			<input id="input-giocatore" list="lista-giocatori" name="input-giocatore">
			<datalist id="lista-giocatori">
		`;

		// Imposta la callback per l'input
		let inputGiocatoreElement = document.getElementById("input-giocatore");
		inputGiocatoreElement.addEventListener("input", async () => {
			let listaGiocatoriElement = document.getElementById("lista-giocatori");

			let response = await fetch(`${API_URL}/get_giocatori.php?cognome_nome=${inputGiocatoreElement.value}&limit=10`);
			if (!response.ok) {
				listaGiocatoriElement.innerHTML = "Errore durante il caricamento dei giocatori";
				return;
			}

			let giocatori = await response.json();

			listaGiocatoriElement.innerHTML = `
				${giocatori.map(giocatore => `
					<option value="${giocatore.cognome_nome}" data-giocatore-id="${giocatore.giocatore_id}">${giocatore.posizione}</option>
				`).join('')}
			`;
		});

		// Imposta la callback per la selezione di un giocatore
		inputGiocatoreElement.addEventListener("change", async () => {
			let listaGiocatoriElement = document.getElementById("lista-giocatori");

			let giocatoreId = listaGiocatoriElement.querySelector(`option[value="${inputGiocatoreElement.value}"]`).dataset.giocatoreId;

			await this.apriSchedaGiocatore(giocatoreId);
		});

		inputGiocatoreElement.focus();
	}

	/**
	 * Apre la scheda del giocatore selezionato.
	 * @param {number} giocatoreId ID del giocatore
	 * @returns {Promise<void>}
	 */
	async apriSchedaGiocatore(giocatoreId) {
		// Selezione del giocatore
		this.giocatoreInAsta = giocatoreId;

		// Ottieni le informazioni del giocatore
		let response = await fetch(`${API_URL}/get_giocatore.php?giocatore_id=${giocatoreId}`);
		if (!response.ok) {
			astaMainElement.innerHTML = "Errore durante il caricamento del giocatore";
			return;
		}

		let giocatore = await response.json();

		// Mostra le informazioni sul giocatore e il menu per aggiungerlo
		astaMainElement.innerHTML = `
			<h2>
				<!-- Pulsante per tornare indietro -->
				<a id="go-back" href="#">&lt; Indietro</a>

				${giocatore.cognome_nome} (${giocatore.posizione})
			</h2>

			<p>Assegna a</p>
			<select id="select-fantasquadra">
			</select>

			<p>Crediti spesi</p>
			<input id="input-crediti" type="number" min="0" max="${giocatore.crediti_finali}" value="${giocatore.crediti_finali}">
			<br>

			<br>
			<button id="aggiungi-giocatore">Aggiungi</button>
		`;

		// Imposta la callback per il pulsante go-back
		let goBackElement = document.getElementById("go-back");
		goBackElement.addEventListener("click", async () => {
			await this.apriSelettoreGiocatore();
		});

		// Riempi la select con le fantasquadre
		let selectFantasquadraElement = document.getElementById("select-fantasquadra");

		response = await fetch(`${API_URL}/get_fantasquadre.php?fantalega_id=${this.fantalegaId}`);
		if (!response.ok) {
			selectFantasquadraElement.innerHTML = "Errore durante il caricamento delle squadre";
			return;
		}

		let fantasquadre = await response.json();

		selectFantasquadraElement.innerHTML = `
			${fantasquadre.map(fantasquadra => `
				<option value="${fantasquadra.fantasquadra_id}">${fantasquadra.nome}</option>
			`).join('')}
		`;

		// Imposta la callback per il pulsante aggiungi-giocatore
		let aggiungiGiocatoreElement = document.getElementById("aggiungi-giocatore");

		aggiungiGiocatoreElement.addEventListener("click", async () => {
			let fantasquadraId = selectFantasquadraElement.value;
			let crediti = document.getElementById("input-crediti").value;

			await this.assegnaGiocatore(fantasquadraId, crediti);
		});
	}

	/**
	 * Assegna il giocatore alla fantasquadra.
	 * @param {number} fantasquadraId ID della fantasquadra
	 * @param {number} creditiSpesi Crediti spesi per il giocatore
	 * @returns {Promise<void>}
	 */
	async assegnaGiocatore(fantasquadraId, creditiSpesi) {
		// Ottieni informazioni sulla fantasquadra
		let response = await fetch(`${API_URL}/get_fantasquadra.php?fantasquadra_id=${fantasquadraId}`);
		if (!response.ok) {
			alert("Errore durante il caricamento della fantasquadra");
			return;
		}

		let fantasquadra = await response.json();

		// Ottieni informazioni sul giocatore
		response = await fetch(`${API_URL}/get_giocatore.php?giocatore_id=${this.giocatoreInAsta}`);
		if (!response.ok) {
			alert("Errore durante il caricamento del giocatore");
			return;
		}

		let giocatore = await response.json();

		// Controlla che i crediti siano validi
		if (fantasquadra.crediti < 0) {
			alert("I crediti spesi non possono essere negativi");
			return;
		}

		// Controlla che i crediti siano sufficienti
		if (fantasquadra.crediti - creditiSpesi < 0) {
			alert("Crediti insufficienti");
			return;
		}

		// Controlla che la fantasquadra non sfori il numero massimo di giocatori per ruolo
		response = await fetch(`${API_URL}/get_giocatori_fantasquadra.php?fantasquadra_id=${fantasquadraId}`);
		if (!response.ok) {
			alert("Errore durante il caricamento dei giocatori della fantasquadra");
			return;
		}

		let giocatoriFantasquadra = await response.json();

		let giocatoriPerRuolo = {
			"P": 0,
			"D": 0,
			"C": 0,
			"A": 0
		};
		let maxGiocatoriPerRuolo = {
			"P": this.numeroPortieri,
			"D": this.numeroDifensori,
			"C": this.numeroCentrocampisti,
			"A": this.numeroAttaccanti
		};

		giocatoriFantasquadra.forEach(giocatore => {
			giocatoriPerRuolo[giocatore.posizione]++;
		});

		giocatoriPerRuolo[giocatore.posizione]++;

		if (giocatoriPerRuolo[giocatore.posizione] > maxGiocatoriPerRuolo[giocatore.posizione]) {
			alert("Numero massimo di giocatori per ruolo raggiunto");
			return;
		}

		// Assegna il giocatore
		response = await fetch(`${API_URL}/assegna_giocatore.php`,{
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify({
				"fantasquadra_id": fantasquadraId,
				"giocatore_id": this.giocatoreInAsta,
				"crediti_spesi": creditiSpesi
			})
		});
		if (!response.ok) {
			alert("Errore durante l'aggiunta del giocatore");
			return;
		}

		await this.updateInfoFantasquadre();
		await this.apriSelettoreGiocatore();
	}

	async svincolaGiocatore(giocatoreId, fantasquadraId) {
		let response = await fetch(`${API_URL}/svincola_giocatore.php`, {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify({
				"fantasquadra_id": fantasquadraId,
				"giocatore_id": giocatoreId
			})
		});
		if (!response.ok) {
			alert("Errore durante lo svincolo del giocatore");
			return;
		}

		await this.updateInfoFantasquadre();
		await this.apriSelettoreGiocatore();
	}

	/**
	 * Aggiorna la sezione info-fantasquadre.
	 * @returns {Promise<void>}
	 */
	async updateInfoFantasquadre() {
		let response = await fetch(`${API_URL}/get_fantasquadre.php?fantalega_id=${this.fantalegaId}`);
		if (!response.ok) {
			infoFantasquadreElement.innerHTML = "Errore durante il caricamento delle squadre";
			return;
		}

		let fantasquadre = await response.json();

		let html = `
			<h2>Squadre</h2>
			<div id="fantasquadre">
		`;

		for (let fantasquadra of fantasquadre) {
			response = await fetch(`${API_URL}/get_giocatori_fantasquadra.php?fantasquadra_id=${fantasquadra.fantasquadra_id}`);
			if (!response.ok) {
				infoFantasquadreElement.innerHTML = "Errore durante il caricamento dei giocatori della squadra";
				return;
			}
	
			let giocatori = await response.json();

			// Ordina i giocatori per posizione
			giocatori.sort(defaultGiocatoriComparator);

			html += `
				<div class="fantasquadra">
					<p>${fantasquadra.nome} (${fantasquadra.crediti} crediti)</p>
					<ul>
			`;

			if (giocatori.length > 0) {
				for (let giocatore of giocatori) {
					response = await fetch(`${API_URL}/get_crediti_spesi_per_giocatore.php?giocatore_id=${giocatore.giocatore_id}&fantasquadra_id=${fantasquadra.fantasquadra_id}`);
					if (!response.ok) {
						infoFantasquadreElement.innerHTML = "Errore durante il caricamento dei crediti spesi per il giocatore";
						return;
					}

					let creditiSpesi = await response.json();

					html += `<li>
						${giocatore.cognome_nome} (${giocatore.posizione}, crediti spesi: ${creditiSpesi.crediti_spesi})
						<a href="javascript:svincolaGiocatore(${giocatore.giocatore_id}, ${fantasquadra.fantasquadra_id});">🗑</a>
					</li>`;
				}
			} else {
				html += `<li>Nessun giocatore</li>`;
			}

			html += `
					</ul>
				</div>
			`;
		}

		html += `
			</div>
		`;

		infoFantasquadreElement.innerHTML = html;
	}
}

/**
 * L'oggetto globale Asta (solo uno per sessione).
 * @type {Asta}
 */
let asta;

async function main() {
	// Otteniamo le informazioni dell'utente loggato
	let utente = await getInfoUtente();
	if (utente == null) {
		window.location.href = "login.html";
		return;
	}

	// Otteniamo l'ID della fantalega
	let params = new URLSearchParams(window.location.search);

	if (!params.has('fantalega_id')) {
		infoFantalegaElement.innerHTML = "ID fantalega non specificato";
		return;
	}

	let fantalegaId = params.get('fantalega_id');

	// Otteniamo le informazioni della fantalega
	let response = await fetch(`${API_URL}/get_fantalega.php?fantalega_id=${fantalegaId}`);
	if (!response.ok) {
		mainContentElement.innerHTML = "Errore durante il caricamento della fantalega";
		return;
	}

	let fantalega = await response.json();

	// L'utente loggato deve essere l'admin della fantalega
	if (utente.utente_id != fantalega.admin_id) {
		mainContentElement.innerHTML = "Non sei l'admin di questa fantalega";
		return;
	}

	// Prima fase: impostazioni dell'asta (crediti iniziali)
	window.svincolaGiocatore = async function (fantasquadraId, giocatoreId) {
		await asta.svincolaGiocatore(fantasquadraId, giocatoreId);
	};

	asta = new Asta(
		fantalegaId,
		fantalega.crediti_iniziali,
		fantalega.numero_portieri,
		fantalega.numero_difensori,
		fantalega.numero_centrocampisti,
		fantalega.numero_attaccanti
	);
	await asta.start();
}

main();
