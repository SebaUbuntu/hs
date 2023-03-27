const bodyParser = require("body-parser");
const cors = require('cors');
const express = require('express');
const fs = require('fs');
const path = require("path");

const DEBUG = true;
const PORT = process.env.PORT || 3000;

const TENTATIVI_FILENAME = "tentativi.json";
const DOMANDE_FILENAME = "domande.json";

/**
 * [
 *     {
 *  	 "nome": string,
 * 	     "cognome": string,
 * 	     "punteggio": number,
 *       "risultato": string,
 *     },
 * ]
 */
let tentativi = [];

/**
 * [
 *     {
 *  	 "domanda": string,
 * 	     "risposte": string[],
 *       "corretta": number,
 *     },
 * ]
 */
let domande = [];

// Carica i dati dai file JSON se esistono
fs.readFile(TENTATIVI_FILENAME, function (err, data) {
	if (err) {
		if (err.code != "ENOENT") {
			console.error(err);
		}
	} else {
		tentativi = JSON.parse(data);
	}
});
fs.readFile(DOMANDE_FILENAME, function (err, data) {
	if (err) {
		console.error("Impossibile leggere le domande da file: " + err);
	} else {
		domande = JSON.parse(data);
	}
});

// Crea l'app Express
let app = express();

// Attiva CORS
app.use(cors({
	origin: '*'
}));

// Attiva il middleware per il parsing del body delle richieste
app.use(bodyParser.urlencoded({ extended: true }));

// Specifica la cartella contenente i file statici
app.use(express.static(path.join(__dirname, 'public')));

// Attiva pug come motore di template
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'pug');

app.get("/quiz", function(req, res) {
	res.render("quiz", { domande: domande });
});

app.post("/send_quiz", function(req, res) {
	let risultato = {
		"nome": req.body.nome,
		"cognome": req.body.cognome,
		"punteggio": 0,
		"risultato": "Scarso"
	};

	if (DEBUG) {
		console.log(req.body);
	}

	domande.forEach(domanda => {
		let domandaIndex = domande.indexOf(domanda);
		let rispostaUtente = req.body[domandaIndex];

		if (DEBUG) {
			console.log("Risposta alla domanda " + domandaIndex + ": " + req.body[domandaIndex]);
		}

		if (rispostaUtente == domanda.corretta) {
			risultato["punteggio"] += 1.5;
		}
	});

	if (risultato["punteggio"] <= 1.5) {
		risultato["risultato"] = "Scarso";
	} else if (risultato["punteggio"] <= 3) {
		risultato["risultato"] = "Buono";
	} else {
		risultato["risultato"] = "Ottimo";
	}

	tentativi.push(risultato);

	// Scrivi su file JSON i dati
	fs.writeFile(TENTATIVI_FILENAME, JSON.stringify(tentativi), (data) => {});

	res.render("riepilogo", { risultato: risultato });
});

app.get("/tentativi", function(req, res) {
	res.render("tentativi", { tentativi: tentativi.sort((a, b) => a["punteggio"] - b["punteggio"]).reverse() });
});

// Avvia il server
app.listen(PORT, function () {
	console.log("http://localhost:" + PORT + "/");
});
