const cors = require('cors');
const dotenv = require('dotenv');
const express = require('express');
const fetch = (...args) => import('node-fetch').then(({default: fetch}) => fetch(...args));
const fs = require('fs');
const path = require("path");

// Carica le variabili d'ambiente
dotenv.config();

const DEBUG = process.env.DEBUG !== undefined ? process.env.DEBUG : true;
const PORT = process.env.PORT || 3000;
const OPENWEATHERMAP_API_KEY = process.env.OPENWEATHERMAP_API_KEY;

const PIATTI_TIPICI_PER_REGIONE_URL = "http://192.168.185.20:3000/ricercaPiatti"

const PROVINCE_FILENAME = path.join(__dirname, "province.json");
const RECENSIONI_FILENAME = path.join(__dirname, "recensioni.json");
const RISTORANTI_FILENAME = path.join(__dirname, "ristoranti.json");

// Controlla che province.json esista
if (!fs.existsSync(PROVINCE_FILENAME)) {
	console.error(`File ${PROVINCE_FILENAME} non trovato`);
	process.exit(1);
}

// Crea i file JSON se non esistono
if (!fs.existsSync(RECENSIONI_FILENAME)) {
	fs.writeFileSync(RECENSIONI_FILENAME, "[]");
}

if (!fs.existsSync(RISTORANTI_FILENAME)) {
	fs.writeFileSync(RISTORANTI_FILENAME, "[]");
}

// Crea l'app Express
let app = express();

// Attiva CORS
app.use(cors({
	origin: '*'
}));

// Attiva il middleware per il parsing del body delle richieste
app.use(express.json());

// Specifica la cartella contenente i file statici
app.use(express.static(path.join(__dirname, 'public')));

app.get("/api/province", async (req, res) => {
	let province = JSON.parse(await fs.promises.readFile(PROVINCE_FILENAME));
	res.json(province);
});

app.get("/api/province/:sigla", async (req, res) => {
	let province = JSON.parse(await fs.promises.readFile(PROVINCE_FILENAME));

	let provincia = province.find(p => p.sigla == req.params.sigla);
	if (provincia === undefined) {
		res.status(404).send("Provincia non trovata");
		return;
	}

	res.json(provincia);
});

app.get("/api/province/:sigla/meteo", async (req, res) => {
	let province = JSON.parse(await fs.promises.readFile(PROVINCE_FILENAME));

	let provincia = province.find(p => p.sigla == req.params.sigla);
	if (provincia === undefined) {
		res.status(404).send("Provincia non trovata");
		return;
	}

	let response = await fetch(`https://api.openweathermap.org/data/2.5/weather?q=${provincia.nome}, IT&lang=IT&appid=${OPENWEATHERMAP_API_KEY}`);
	if (!response.ok) {
		console.error(`Errore durante il caricamento del meteo per la provincia ${provincia.sigla}: ${response.status}`);
		res.status(500).send("Errore durante il caricamento del meteo");
		return;
	}

	let meteo = await response.json();

	res.json(meteo);
});

app.get("/api/regioni", async (req, res) => {
	let province = JSON.parse(await fs.promises.readFile(PROVINCE_FILENAME));

	let regioni = new Set();
	province.forEach(provincia => {
		regioni.add(provincia.regione);
	});

	res.json(Array.from(regioni));
});

app.get("/api/regioni/:regione/piatti/:tipologia", async (req, res) => {
	let regione = req.params.regione;
	let tipologia = req.params.tipologia;

	let response = await fetch(`${PIATTI_TIPICI_PER_REGIONE_URL}?regioni=${regione}&tipologia=${tipologia}`);
	if (!response.ok) {
		console.error(`Errore durante il caricamento dei piatti tipici per la regione ${regione}: ${response.status}`);
		res.status(500).send("Errore durante il caricamento dei piatti tipici");
		return;
	}

	let piatti = await response.json();

	res.json(piatti.messaggio.lista);
});

/**
 * @api {get} /api/ristoranti Lista ristoranti
 * @apiDescription Ottiene la lista dei ristoranti
 * @apiParam {String} [provincia] Filtra per provincia
 * @apiParam {String} [regione] Filtra per regione
 * @apiSuccess {Object[]} ristoranti Lista dei ristoranti
 */
app.get("/api/ristoranti", async (req, res) => {
	let provincia = req.query.provincia;
	let regione = req.query.regione;

	// Provincia e regione non possono essere specificate contemporaneamente
	if (provincia && regione) {
		res.status(400).send("Provincia e regione non possono essere specificate contemporaneamente");
		return;
	}

	let ristoranti = JSON.parse(await fs.promises.readFile(RISTORANTI_FILENAME));
	let province = JSON.parse(await fs.promises.readFile(PROVINCE_FILENAME));

	// Filtra per provincia o regione
	if (provincia) {
		ristoranti = ristoranti.filter(r => r.provincia == provincia);
	} else if (regione) {
		ristoranti = ristoranti.filter(r => province.find(p => p.sigla == r.provincia).regione == regione);
	}

	res.json(ristoranti);
});

app.post("/api/ristoranti", async (req, res) => {
	// Valida i dati
	if (
		// Dati definiti
		!req.body.nome || !req.body.provincia || !req.body.indirizzo || !req.body.anno_apertura || !req.body.specialita
		// Provincia valida
		|| JSON.parse(await fs.promises.readFile(PROVINCE_FILENAME)).find(p => p.sigla == req.body.provincia) === undefined
		// Anno di apertura valido
		|| isNaN(req.body.anno_apertura)
	) {
		res.status(400).send("Dati non validi");
		return;
	}

	let ristoranti = JSON.parse(await fs.promises.readFile(RISTORANTI_FILENAME));
	let ristorante = {
		"ristorante_id": (ristoranti.length + 1),
		"nome": req.body.nome,
		"provincia": req.body.provincia,
		"indirizzo": req.body.indirizzo,
		"anno_apertura": req.body.anno_apertura,
		"specialita": req.body.specialita,
	}

	ristoranti.push(ristorante);

	// Scrivi su file JSON i dati
	await fs.promises.writeFile(RISTORANTI_FILENAME, JSON.stringify(ristoranti));

	res.json(ristorante);
});

app.get("/api/ristoranti/:ristorante_id", async (req, res) => {
	let ristoranti = JSON.parse(await fs.promises.readFile(RISTORANTI_FILENAME));

	let ristorante = ristoranti.find(r => r.ristorante_id == req.params.ristorante_id);
	if (!ristorante) {
		res.status(404).send("Ristorante non trovato");
		return;
	}

	res.json(ristorante);
});

app.delete("/api/ristoranti/:ristorante_id", async (req, res) => {
	let ristoranti = JSON.parse(await fs.promises.readFile(RISTORANTI_FILENAME));

	let ristorante = ristoranti.find(r => r.ristorante_id == req.params.ristorante_id);
	if (!ristorante) {
		res.status(404).send("Ristorante non trovato");
		return;
	}

	let recensioni = JSON.parse(await fs.promises.readFile(RECENSIONI_FILENAME));

	// Rimuovi le recensioni collegate al ristorante
	recensioni = recensioni.filter(r => r.ristorante_id != req.params.ristorante_id);

	// Scrivi su file JSON i dati
	await fs.promises.writeFile(RECENSIONI_FILENAME, JSON.stringify(recensioni));

	// Rimuovi il ristorante
	ristoranti = ristoranti.filter(r => r.ristorante_id != req.params.ristorante_id);

	// Scrivi su file JSON i dati
	await fs.promises.writeFile(RISTORANTI_FILENAME, JSON.stringify(ristoranti));

	res.json(ristorante);
});

app.get("/api/ristoranti/:ristorante_id/recensioni", async (req, res) => {
	let recensioni = JSON.parse(await fs.promises.readFile(RECENSIONI_FILENAME));

	let recensioniRistorante = recensioni.filter(r => r.ristorante_id == req.params.ristorante_id);

	res.json(recensioniRistorante);
});

app.post("/api/ristoranti/:ristorante_id/recensioni", async (req, res) => {
	let recensioni = JSON.parse(await fs.promises.readFile(RECENSIONI_FILENAME));
	let ristoranti = JSON.parse(await fs.promises.readFile(RISTORANTI_FILENAME));

	let ristorante = ristoranti.find(r => r.ristorante_id == req.params.ristorante_id);
	if (!ristorante) {
		res.status(404).send("Ristorante non trovato");
		return;
	}

	// Valida i dati
	if (
		// Dati definiti
		!req.body.stelle || !req.body.commento
		// Stelle valide
		|| isNaN(req.body.stelle) || req.body.stelle < 1 || req.body.stelle > 5
	) {
		res.status(400).send("Dati non validi");
		return;
	}

	let recensione = {
		"recensione_id": recensioni.length + 1,
		"ristorante_id": req.params.ristorante_id,
		// Crea una data ora
		"data": Math.round(new Date().getTime() / 1000),
		"stelle": req.body.stelle,
		"commento": req.body.commento,
	};

	recensioni.push(recensione);

	// Scrivi su file JSON i dati
	await fs.promises.writeFile(RECENSIONI_FILENAME, JSON.stringify(recensioni));

	res.json(recensione);
});

app.get("/api/ristoranti/:ristorante_id/recensioni/:recensione_id", async (req, res) => {
	let recensioni = JSON.parse(await fs.promises.readFile(RECENSIONI_FILENAME));
	let recensione = recensioni.find(r => r.recensione_id == req.params.recensione_id);

	if (!recensione) {
		res.status(404).send("Recensione non trovata");
		return;
	}

	res.json(recensione);
});

app.delete("/api/ristoranti/:ristorante_id/recensioni/:recensione_id", async (req, res) => {
	let recensioni = JSON.parse(await fs.promises.readFile(RECENSIONI_FILENAME));

	let recensione = recensioni.find(r => r.recensione_id == req.params.recensione_id);
	if (!recensione) {
		res.status(404).send("Recensione non trovata");
		return;
	}

	// Rimuovi la recensione
	recensioni = recensioni.filter(r => r.recensione_id != req.params.recensione_id);

	// Scrivi su file JSON i dati
	await fs.promises.writeFile(RECENSIONI_FILENAME, JSON.stringify(recensioni));
});

// Avvia il server
app.listen(PORT, function () {
	console.log("http://localhost:" + PORT + "/");
});
