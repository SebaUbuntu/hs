const bodyParser = require("body-parser");
const cookieParser = require("cookie-parser");
const cors = require('cors');
const express = require("express");
const fs = require('fs');
const path = require("path");
const session = require('express-session');

const PORT = 3000;
const FILENAME = "meteo.json";

let dati = [
	{
		citta: "Napoli",
		minTemp: "",
		maxTemp: "",
		mediaTemp: ""
	},
	{
		citta: "Parma",
		minTemp: "",
		maxTemp: "",
		mediaTemp: ""
	},
	{
		citta: "Milano",
		minTemp: "",
		maxTemp: "",
		mediaTemp: ""
	},
	{
		citta: "Firenze",
		minTemp: "",
		maxTemp: "",
		mediaTemp: ""
	}
];

// Crea l'app Express
let app = express();
app.use(cors({
	origin: '*'
}));

// Specifica la cartella contenente i file statici
app.use(express.static(path.join(__dirname, 'public')));

// Attiva il middleware per il parsing del body delle richieste
app.use(bodyParser.urlencoded({ extended: true }));

// Imposta il nome dell'applicazione
app.set('appName', 'Meteo');

// Attiva pug come motore di template
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'pug');

// Attiva il middleware per la gestione delle sessioni
app.use(cookieParser());
app.use(session({
	name: "sessionId",
	secret: "segreto",
	saveUninitialized: true,
	cookie: {
		secure: false,
		maxAge: 1000 * 60 // un minuto
	},
	resave: false
}));
app.use(cookieParser());

// Carica i dati da file JSON se esiste
fs.readFile(FILENAME, function (err, data) {
	if (err) {
		console.error(err);
	} else {
		dati = JSON.parse(data);
	}
});

// Route per la pagina di riepilogo
app.get("/riepilogo", function (req, res) {
	res.render("riepilogo", { temperature: dati });
});

app.get("/form", function (req, res) {
	res.render("form", { temperature: dati });
});

// Route per ricevere i dati del form
app.get("/update_data", function (req, res) {
	// Funzione asincrona per scrivere su file JSON
	async function fun() {
		try {
			aggiorna(req.query.citta, req.query.min.toString(), req.query.max.toString());

			res.render("riepilogo", { temperature: dati });

			// Scrivi su file JSON i dati
			await fs.promises.writeFile(FILENAME, JSON.stringify(dati));
		} catch (err) {
			console.log(err);
		}
	}

	// Avvia la funzione asincrona
	fun();
});

function aggiorna(citta, min, max) {
	for (let n = 0; n < dati.length; n++) {
		let datiCitta = dati[n];
		if (datiCitta.citta != citta) {
			continue;
		}

		if (datiCitta.minTemp == "") {
			datiCitta.minTemp = min;
		} else if (+datiCitta.minTemp > +min) {
			datiCitta.minTemp = min;
		}

		if (datiCitta.maxTemp == "") {
			datiCitta.maxTemp = max;
		} else if (+datiCitta.maxTemp < +max) {
			datiCitta.maxTemp = max;
		}

		datiCitta.mediaTemp = (((+datiCitta.minTemp) + (+datiCitta.maxTemp)) / 2).toString();
	}
}

// Avvia il server
app.listen(PORT, function () {
	console.log("https://localhost:" + PORT + "/");
});
